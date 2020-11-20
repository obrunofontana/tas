import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';
import { ItemSaleComponent } from './item-sale/item-sale.component';
import {
  CustomerEntity,
  CustomerService,
} from '../../services/customer.service';
import {
  SaleOrderEntity,
  SaleOrderService,
} from '../../services/sale-order.service';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.scss'],
})
export class SalesComponent implements OnInit {
  public displayedColumns: string[] = [
    'code',
    'customer',
    'billingDate',
    'salesDate',
    'total',
    'options',
  ];

  public customers: CustomerEntity[] = [];
  public sales: SaleOrderEntity[] = [];
  public sale: SaleOrderEntity = new SaleOrderEntity();

  public loading: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(
    private service: SaleOrderService,
    private customerService: CustomerService,
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
          this.customerService.findAll().subscribe((result) => {
            this.customers = result as [];
          });

          this.sales = result as [];
        },
        (error) => {
          this.showError('Ops! Alconteceu algo...', error);
        }
      )
      .add(() => {
        this.loading = false;
      });
  }

  private openSidenav(sale: SaleOrderEntity): void {
    this.sale = sale;
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
    this.openSidenav(new SaleOrderEntity());
  }

  public preview(sale: SaleOrderEntity): void {
    this.openSidenav(sale);
  }

  public remove(sale: SaleOrderEntity): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loading = true;

        this.service
          .remove(sale.id)
          .subscribe(
            (result) => {
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
      .save(this.sale)
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
    let dialogRef = this.dialog.open(ItemSaleComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.sale.items.push(result);
      }
    });
  }
}
