import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';
import {
  SupplierEntity,
  SupplierService,
} from '../../services/supplier.service';

@Component({
  selector: 'app-suppliers',
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.scss'],
})
export class SuppliersComponent implements OnInit {
  public displayedColumns: string[] = [
    'code',
    'name',
    'cnpj',
    'address',
    'email',
    'contact',
    'active',
    'options',
  ];
  public suppliers: SupplierEntity[] = [];
  public supplier: SupplierEntity = new SupplierEntity();

  public loading: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(
    private service: SupplierService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.errorMessage = '';
    this.loading = true;
    this.service
      .findAll()
      .subscribe(
        (result) => {
          this.suppliers = result as [];
        },
        (error) => {
          this.showError('Ops! Alconteceu algo...', error);
        }
      )
      .add(() => {
        this.loading = false;
      });
  }

  private openSidenav(supplier: SupplierEntity): void {
    this.supplier = supplier;
    this.sidenav.open();
  }

  private showError(text: string, error: any): void {
    this.snackBar.open(text, '', {
      duration: 5000,
      panelClass: 'snackError',
    });

    this.errorMessage = error.status == 0 ? 'Não foi possível conectar ao servidor' : error.message;
  }

  public add(): void {
    this.openSidenav(new SupplierEntity());
  }

  public edit(supplier: SupplierEntity): void {
    this.openSidenav(Object.assign({}, supplier));
  }

  public remove(supplier: SupplierEntity): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loading = true;

        this.service
          .remove(supplier.id)
          .subscribe(
            () => {
              this.snackBar.open('Registro excluído com sucesso!', '', {
                duration: 3500,
              });

              this.ngOnInit();
            },
            (error) => {
              this.showError('Não foi possível exluir o registro', error);
            }
          )
          .add(() => {
            this.loading = false;
          });
      }
    });
  }

  public confirm(): void {
    this.loading = true;
    this.service
      .save(this.supplier)
      .subscribe(
        () => {
          this.snackBar.open('Registro salvo com sucesso!', '', {
            duration: 3500,
          });

          // Workaround reload
          this.ngOnInit();
        },
        (error) => {
          this.showError('Não foi possível salvar o registro', error);
        }
      )
      .add(() => {
        this.loading = false;
        this.sidenav.close();
      });
  }
}
