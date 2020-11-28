import { GroupEntity, GroupService } from '../../services/group.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss'],
})
export class GroupsComponent implements OnInit {
  public displayedColumns: string[] = ['name', 'options'];
  public errorMessage: string;
  public groups: GroupEntity[] = [];
  public group: GroupEntity = new GroupEntity();
  public loading: boolean;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(
    private service: GroupService,
    private snack: MatSnackBar,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.errorMessage = '';
    this.loading = true;

    this.service
      .findAll()
      .subscribe(
        (result) => {
          this.groups = result as [];
        },
        (error) => {
          this.showError('Ops! Alconteceu algo...', error);
        }
      )
      .add(() => {
        this.loading = false;
      });
  }

  private openSidenav(grupo: GroupEntity): void {
    this.group = grupo;
    this.sidenav.open();
  }

  private showError(text: string, error: any): void {
    this.snack.open(text, '', {
      duration: 5000,
      panelClass: 'snackError',
    });

    this.errorMessage = error.status == 0 ? 'Não foi possível conectar ao servidor' : error.message;
  }

  public add(): void {
    this.openSidenav(new GroupEntity());
  }

  public edit(group: GroupEntity): void {
    this.openSidenav(Object.create(group));
  }

  public remove(grupo: GroupEntity): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loading = true;

        this.service
          .remove(grupo.id)
          .subscribe(
            () => {
              this.snack.open('Registro excluído com sucesso!', '', {
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
      .save(this.group)
      .subscribe(
        () => {
          this.snack.open('Registro salvo com sucesso!', '', {
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
