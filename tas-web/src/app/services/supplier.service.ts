import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { People } from './customer.service';

@Injectable({
  providedIn: 'root',
})
export class SupplierService {
  constructor(private http: HttpClient) {}

  public findAll() {
    return this.http.get(environment.urlSRV + '/api/suppliers');
  }

  public save(supplier: SupplierEntity) {
    if (supplier.id) {
      return this.update(supplier);
    } else {
      return this.create(supplier);
    }
  }

  public remove(id: number) {
    return this.http.delete(environment.urlSRV + '/api/suppliers/' + id);
  }

  private create(supplier: SupplierEntity) {
    return this.http.post(environment.urlSRV + '/api/suppliers', supplier);
  }

  private update(supplier: SupplierEntity) {
    return this.http.put(
      environment.urlSRV + '/api/suppliers/' + supplier.id,
      supplier
    );
  }
}
export class SupplierEntity extends People {
  contact: string;
  cnpj: string;
}
