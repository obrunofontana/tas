import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';
// import { ClienteEntity, ClienteService } from '../../services/services/cliente.service';


@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  public displayedColumns: string[] = ['codigo','nome','cpf','email', 'endereco', 'ativo', 'options'];

  public clientes: ClienteEntity[] = [];
  public cliente: ClienteEntity = new ClienteEntity();

  //Variaveis de controle
  public loading: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(private service: ClienteService, private snakBar: MatSnackBar,
    private dialog: MatDialog) { }

  ngOnInit(): void {
  }

}
