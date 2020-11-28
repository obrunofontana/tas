import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SaleOrderEntity } from './sale-order.service';

@Injectable({
  providedIn: 'root',
})
export class InvoiceService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.urlSRV;
  }

  public findAll() {
    return this.http.get(`${this.url}/api/invoices`);
  }

  public save(invoice: InvoiceEntity) {
    if (invoice.id) {
      return this.update(invoice);
    } else {
      return this.create(invoice);
    }
  }

  public remove(id: number) {
    return this.http.delete(`${this.url}/api/invoices/${id}`);
  }

  private create(invoice: InvoiceEntity) {
    return this.http.post(`${this.url}/api/invoices`, invoice);
  }

  private update(invoice: InvoiceEntity) {
    return this.http.put(`${this.url}/api/invoices/${invoice.id}`, invoice);
  }
}

export class InvoiceEntity {
  id: number;
  code: string;
  invoiceDate: Date;
  salesOrder: SaleOrderEntity;
}
