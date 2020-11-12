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
  // Default values
  public displayedColumns: string[] = ['nome', 'options'];
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

  /**
   * Método disparado na inicialização do componente, logo após sua construção
   */
  ngOnInit(): void {
    //Inicia as variáveis
    this.errorMessage = '';
    this.loading = true;

    //Carrega todos os registros
    this.service
      .listAll()
      .subscribe(
        (result) => {
          // Alimenta o datasource da tabela com os registros recebidos da service
          this.groups = result as [];
        },
        (error) => {
          // Se ocorreu algum erro neste processo, mostra mensagem para usuário
          this.showError('Ops! Alconteceu algo...', error);
        }
      )
      .add(() => {
        // Após a execução do subscribe, dando erro ou não, oculta a barra de progresso
        this.loading = false;
      });
  }

  /**
   * Dá um open na sidnav exibindo o formulário com os dados
   * da objeto passado por parâmetro.
   *
   * @param grupo
   */
  private openSidenav(grupo: GroupEntity): void {
    this.group = grupo;
    this.sidenav.open();
  }

  /**
   * Função responsável por mostrar uma mensagem de erro padrão.
   * @param text
   * @param error
   */
  private showError(text: string, error: any): void {
    //Mostra a snackbar com fundo customizado (vermelho)
    this.snack.open(text, '', {
      duration: 5000,
      panelClass: 'snackWarn',
    });

    //Adiciona a mensagem de erro no painel de erro
    this.errorMessage =
      error.status == 0
        ? 'Não foi possível conectar ao servidor'
        : error.message;
  }

  /**
   * Abre o formulário com um novo cliente para inclusão
   */
  public add(): void {
    this.openSidenav(new GroupEntity());
  }

  /**
   * Abre o formulário com os campos preenchidos com os valores
   * do parametro.
   *
   * @param group
   */
  public edit(group: GroupEntity): void {
    this.openSidenav(Object.create(group));
  }

  /**
   * Chama a janela de confirmação de exclusão, se usuário confirmar
   * chama evento de exclusão da service.
   *
   * @param grupo
   */
  public remove(grupo: GroupEntity): void {
    //Mostra a janela modal de confirmação
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
    });

    //Depois de fechado (clicado em cancelar ou confirmar)...
    dialogRef.afterClosed().subscribe((result) => {
      //Se confirmou, exclui o registro
      if (result) {
        this.loading = true;

        this.service
          .remove(grupo.id)
          .subscribe(
            (result) => {
              //Deu certo, avisa o usuário...
              this.snack.open('Registro excluído com sucesso!', '', {
                duration: 3500,
              });

              //Atualiza a lista (Ok, esta não é a forma mais inteligente de fazer isto...)
              this.ngOnInit();
            },
            (error) => {
              //Se ocorreu algum erro neste processo, mostra mensagem para usuário
              this.showError('Não foi possível exluir o registro', error);
            }
          )
          .add(() => {
            //Após a execução do subscribe, dando erro ou não, oculta a barra de progresso
            this.loading = false;
          });
      }
    });
  }

  /**
   * Método chamado ao confirmar uma inclusão/alteração
   */
  public confirm(): void {
    //Mostra a barra de progresso
    this.loading = true;

    //Chama o método salvar (incluir ou alterar) da service
    this.service
      .save(this.group)
      .subscribe(
        (result) => {
          //Deu tudo certo, então avise o usuário...
          this.snack.open('Registro salvo com sucesso!', '', {
            duration: 3500,
          });

          //Atualiza a lista (Ok, esta não é a forma mais inteligente de fazer isto...)
          this.ngOnInit();
        },
        (error) => {
          //Se ocorreu algum erro neste processo, mostra mensagem para usuário
          this.showError('Não foi possível salvar o registro', error);
        }
      )
      .add(() => {
        //Após a execução do subscribe, dando erro ou não,
        //oculta a barra de progresso...
        this.loading = false;

        //... e fecha a sidenav com o formulário
        this.sidenav.close();
      });
  }
}
