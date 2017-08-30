import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {Client} from '../models/client';

@Injectable()
export class HttpService {

  private API = 'http://localhost:8080/';

  constructor(private http: Http) {

  }

  getClients(): Observable<Client[]> {
    const url = `${this.API}clients/all`;
    return this.http.get(url)
      .map(res => res.json());
  }

  findClientById(id): Observable<Client> {
    const url = `${this.API}clients/${id}`;
    return this.http.get(url)
      .map(res => res.json());
  }

  addClient(obj: {}): Observable<Response> {
    const url = `${this.API}clients/newclient`;
    return this.http.post(url, obj);
  }
}
