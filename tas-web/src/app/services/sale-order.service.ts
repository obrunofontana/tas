import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductEntity } from './product.service';
import { CustomerEntity } from './customer.service';

@Injectable({
  providedIn: 'root',
})
export class SaleOrderService {
  constructor(private http: HttpClient) {}

  public findAll() {
    return this.http.get(environment.urlSRV + '/api/sales');
  }

  public save(saleOrder: SaleOrderEntity) {
    if (saleOrder.id) {
      return this.update(saleOrder);
    } else {
      return this.create(saleOrder);
    }
  }

  public remove(id: number) {
    return this.http.delete(environment.urlSRV + '/api/sales/' + id);
  }

  private create(saleOrder: SaleOrderEntity) {
    return this.http.post(environment.urlSRV + '/api/sales', saleOrder);
  }

  private update(saleOrder: SaleOrderEntity) {
    return this.http.put(
      environment.urlSRV + '/api/sales/' + saleOrder.id,
      saleOrder
    );
  }
}

export class ItemSaleOrderEntity {
  id: number;
  quantity: number;
  price: number;
  product: ProductEntity;
}

export class SaleOrderEntity {
  id: number;
  code: string;
  salesDate: Date;
  billingDate: Date;
  customer: CustomerEntity;
  items: ItemSaleOrderEntity[];

  constructor() {
    this.items = [];
  }
};
