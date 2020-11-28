import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';
import {
  SaleOrderEntity,
  SaleOrderService,
} from '../../services/sale-order.service';
import { InvoiceEntity, InvoiceService } from '../../services/invoice.service';
import { CustomerEntity, CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-billed-orders',
  templateUrl: './billed-orders.component.html',
  styleUrls: ['./billed-orders.component.scss'],
})
export class BilledOrdersComponent implements OnInit {
  public displayedColumns: string[] = [
    'code',
    // 'customer',
    'invoiceDate',
    // 'salesOrder',
    // 'total',
    'options',
  ];

  public customers: CustomerEntity[] = [];
  public invoices: InvoiceEntity[] = [];
  public invoice: InvoiceEntity = new InvoiceEntity();

  public loading: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(
    private service: InvoiceService,
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

          this.invoices = result as InvoiceEntity[];
        },
        (error) => {
          this.showError('Ops! Alconteceu algo...', error);
        }
      )
      .add(() => {
        this.loading = false;
      });
  }

  private openSidenav(invoice: InvoiceEntity): void {
    this.invoice = invoice;
    this.sidenav.open();
  }

  private showError(text: string, error: any): void {
    this.snackBar.open(text, '', {
      duration: 5000,
      panelClass: 'snackError',
    });

    this.errorMessage =
      error.status == 0
        ? 'Não foi possível conectar ao servidor'
        : error.message;
  }

  public preview(invoice: InvoiceEntity): void {
    this.openSidenav(invoice);
  }

  public remove(invoice: InvoiceEntity): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loading = true;
        this.service
          .remove(invoice.id)
          .subscribe(
            () => {
              this.snackBar.open('Estorno realizado com sucesso!', '', {
                duration: 3500,
              });

              // Workaround reload
              this.ngOnInit();
            },
            (error) => {
              this.showError('Não foi possível estornar o faturamento', error);
            }
          )
          .add(() => {
            this.loading = false;
          });
      }
    });
  }

  public compareOptions(item1, item2) {
    return item1 && item2 && item1.id === item2.id;
  }
}
