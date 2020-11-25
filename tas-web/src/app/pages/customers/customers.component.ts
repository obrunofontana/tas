import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';
import {
  CustomerEntity,
  CustomerService,
} from '../../services/customer.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss'],
})
export class CustomersComponent implements OnInit {
  public displayedColumns: string[] = [
    'code',
    'name',
    'cpf',
    'email',
    'address',
    'active',
    'options',
  ];
  public customers: CustomerEntity[] = [];
  public customer: CustomerEntity = new CustomerEntity();

  public loading: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(
    private service: CustomerService,
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
          console.log('resultado ', result);
          this.customers = result as [];
        },
        (error) => {
          this.showError('Ops! Alconteceu algo...', error);
        }
      )
      .add(() => {
        this.loading = false;
      });
  }

  private openSidenav(customer: CustomerEntity): void {
    this.customer = customer;
    this.sidenav.open();
  }

  private showError(text: string, error: any): void {
    this.snackBar.open(text, '', {
      duration: 5000,
      panelClass: 'snackWarn',
    });

    this.errorMessage = error.status == 0 ? 'Não foi possível conectar ao servidor' : error.message;
  }

  public add(): void {
    this.openSidenav(new CustomerEntity());
  }

  public edit(customer: CustomerEntity): void {
    this.openSidenav(Object.assign({}, customer));
  }

  public remove(customer: CustomerEntity): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loading = true;

        this.service
          .remove(customer.id)
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
      .save(this.customer)
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
