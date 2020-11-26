import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';
import { CompositionItemComponent } from './composition-item/composition-item.component';
import { ProductEntity, ProductService } from '../../services/product.service';

import {
  FinishedProductEntity,
  FinishedProductService,
} from '../../services/composition.service';
@Component({
  selector: 'app-composition',
  templateUrl: './composition.component.html',
  styleUrls: ['./composition.component.scss'],
})
export class CompositionComponent implements OnInit {
  public displayedColumns: string[] = [
    'product',
    'stockMax',
    'stockMin',
    'options'
  ];

  public products: ProductEntity[] = [];
  public finishedProducts: FinishedProductEntity[] = [];
  public finishedProduct: FinishedProductEntity = new FinishedProductEntity();

  public loading: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(
    private service: FinishedProductService,
    private productService: ProductService,
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
          this.productService.findAll().subscribe((result) => {
            this.products = result as [];
          });

          this.finishedProducts = result as [];
        },
        (error) => {
          this.showError('Ops! Alconteceu algo...', error);
        }
      )
      .add(() => {
        this.loading = false;
      });
  }

  private openSidenav(finishedProduct: FinishedProductEntity): void {
    this.finishedProduct = finishedProduct;
    this.sidenav.open();
  }

  private showError(text: string, error: any): void {
    this.snackBar.open(text, '', {
      duration: 5000,
      panelClass: 'snackWarn',
    });

    this.errorMessage =
      error.status == 0
        ? 'Não foi possível conectar ao servidor'
        : error.message;
  }

  public add(): void {
    this.openSidenav(new FinishedProductEntity());
  }

  public preview(finishedProduct: FinishedProductEntity): void {
    this.openSidenav(finishedProduct);
  }

  public remove(finishedProduct: FinishedProductEntity): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loading = true;

        this.service
          .remove(finishedProduct.id)
          .subscribe(
            () => {
              this.snackBar.open('Registro excluído com sucesso!', '', {
                duration: 3500,
              });

              // Workaround reload
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
      .save(this.finishedProduct)
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

  public compareOptions(item1, item2) {
    return item1 && item2 && item1.id === item2.id;
  }

  public addItem(): void {
    let dialogRef = this.dialog.open(CompositionItemComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.finishedProduct.ingredients.push(result);
      }
    });
  }
}
