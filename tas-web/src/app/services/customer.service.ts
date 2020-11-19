import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})

export class CustomerService {
  constructor(private http: HttpClient) {}

  public findAll() {
    return this.http.get(environment.urlSRV + '/api/customers');
  }

  public save(customer: CustomerEntity) {
    if (customer.id) {
      return this.update(customer);
    } else {
      return this.create(customer);
    }
  }

  public remove(id: number) {
    return this.http.delete(environment.urlSRV + '/api/customers/' + id);
  }

  private create(customer: CustomerEntity) {
    return this.http.post(environment.urlSRV + '/api/customers', customer);
  }

  private update(customer: CustomerEntity) {
    return this.http.put(
      environment.urlSRV + '/api/customers/' + customer.id,
      customer
    );
  }
}

export class People {
  id: number;
  code: string;
  name: string;
  email: string;
  address: string;
  active: string;

  constructor() {
    this.active = 'S';
  }
}

export class CustomerEntity extends People {
  cpf: string;
}
