import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GroupService } from './group.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private url: string = environment.urlSRV;

  constructor(private http: HttpClient) {}

  public findAll() {
    return this.http.get(`${this.url}/api/products`);
  }

  public save(product: ProductEntity) {
    if (product.id) {
      return this.update(product);
    } else {
      return this.create(product);
    }
  }

  public remove(id: number) {
    return this.http.delete(`${this.url}/api/products/${id}`);
  }

  private create(product: ProductEntity) {
    return this.http.post(`${this.url}/api/products`, product);
  }

  private update(product: ProductEntity) {
    return this.http.put(`${this.url}/api/products/${product.id}`, product);
  }
}

export class ProductEntity {
  id: number;
  code: string;
  name: string;
  description: string;
  price: number;
  active: string;
  group: GroupService;

  constructor() {
    this.active = 'S';
  }
}
