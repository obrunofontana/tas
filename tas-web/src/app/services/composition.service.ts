import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductEntity } from './product.service';

@Injectable({
  providedIn: 'root',
})
export class FinishedProductService {
  constructor(private http: HttpClient) {}

  public findAll() {
    return this.http.get(environment.urlSRV + '/api/finishedProducts');
  }

  public save(finishedProduct: FinishedProductEntity) {
    if (finishedProduct.id) {
      return this.update(finishedProduct);
    } else {
      return this.create(finishedProduct);
    }
  }

  public remove(id: number) {
    return this.http.delete(environment.urlSRV + '/api/finishedProducts/' + id);
  }

  private create(finishedProduct: FinishedProductEntity) {
    return this.http.post(environment.urlSRV + '/api/finishedProducts', finishedProduct);
  }

  private update(finishedProduct: FinishedProductEntity) {
    return this.http.put(environment.urlSRV + '/api/finishedProducts/' + finishedProduct.id, finishedProduct);
  }
}

export class RawMaterialEntity {
  id: number;
  quantity: number;
  product: ProductEntity;
}

export class FinishedProductEntity {
  id: number;
  product: ProductEntity;
  stockMin: number;
  stockMax: number;
  items: RawMaterialEntity[];

  constructor() {
    this.items = [];
  }
}
1;
