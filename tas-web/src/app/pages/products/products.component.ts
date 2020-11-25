import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';
import { GroupEntity, GroupService } from '../../services/group.service';
import { ProductEntity, ProductService } from '../../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {
  public displayedColumns: string[] = [
    'code',
    'name',
    'price',
    'group',
    'active',
    'options',
  ];

  public groups: GroupEntity[] = [];
  public products: ProductEntity[] = [];
  public product: ProductEntity = new ProductEntity();

  public loading: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(
    private service: ProductService,
    private groupService: GroupService,
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
          this.groupService.findAll().subscribe((result) => {
            this.groups = result as [];
          });

          this.products = result as [];
        },
        (error) => {
          this.showError('Ops! Alconteceu algo...', error);
        }
      )
      .add(() => {
        this.loading = false;
      });
  }

  private openSidenav(product: ProductEntity): void {
    this.product = product;
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
    this.openSidenav(new ProductEntity());
  }

  public edit(product: ProductEntity): void {
    this.openSidenav(Object.assign({}, product));
  }

  public remove(product: ProductEntity): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loading = true;

        this.service
          .remove(product.id)
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
      .save(this.product)
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

  /**
   * Funçao responsável por carregar um item no select, comparando os dois parametros se possuem ID's identicos.
   *
   * @param item1
   * @param item2
   */
  public compareOptions(item1: GroupEntity, item2: GroupEntity) {
    return item1 && item2 && item1.id === item2.id;
  }
}
