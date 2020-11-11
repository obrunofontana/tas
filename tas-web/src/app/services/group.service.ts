import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})


export class GroupEntity {
  id: number;
  name: string;
}
export class GroupService {
  constructor(private http: HttpClient) {}

  private urlAPI: string = environment.urlSRV;

  public listAll() {
    return this.http.get(`${this.urlAPI}/api/groups`);
  }

  public save(group: GroupEntity) {
    if (group.id) {
      return this.update(group);
    } else {
      return this.create(group);
    }
  }

  public remove(id: number) {
    return this.http.delete(`${this.urlAPI}/api/groups/${id}`);
  }

  private create(group: GroupEntity) {
    return this.http.post(environment.urlSRV + '/api/groups', group);
  }

  private update(groups: GroupEntity) {
    return this.http.put(`${this.urlAPI}/api/groups/${groups.id}`, groups);
  }
}
