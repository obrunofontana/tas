import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductEntity } from './product.service';
import { SupplierEntity } from './supplier.service';

@Injectable({
  providedIn: 'root',
})
export class PurchaseService {
  constructor(private http: HttpClient) {}

  public findAll() {
    return this.http.get(environment.urlSRV + '/api/purchases');
  }

  public save(purchase: PurchaseEntity) {
    if (purchase.id) {
      return this.update(purchase);
    } else {
      return this.create(purchase);
    }
  }

  public remove(id: number) {
    return this.http.delete(environment.urlSRV + '/api/purchases/' + id);
  }

  private create(purchase: PurchaseEntity) {
    return this.http.post(environment.urlSRV + '/api/purchases', purchase);
  }

  private update(purchase: PurchaseEntity) {
    return this.http.put(
      environment.urlSRV + '/api/purchases/' + purchase.id,
      purchase
    );
  }
}

export class ItemPurchaseEntity {
  id: number;
  quantity: number;
  price: number;
  product: ProductEntity;
}

export class PurchaseEntity {
  id: number;
  code: string;
  datePurchased: Date;
  supplier: SupplierEntity;
  items: ItemPurchaseEntity[];

  constructor() {
    this.items = [];
  }
}
1;
