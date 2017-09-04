import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {Client} from '../models/client';
import {Product} from "../models/product";


@Injectable()
export class HttpService {

  private API = 'http://localhost:8080/';

  constructor(private http: Http) {
  }

  getClients(): Observable<Client[]> {
    const url = `${this.API}clients`;
    return this.http.get(url)
      .map(res => res.json());
  }

  findClientById(id): Observable<Client> {
    const url = `${this.API}clients/${id}`;
    return this.http.get(url)
      .map(res => res.json());
  }

  addClient(obj: {}): Observable<Response> {
    const url = `${this.API}clients`;
    return this.http.post(url, obj);
  }

  deleteClientById(id) {
    const url = `${this.API}clients/${id}`;
    return this.http.delete(url);
  }

  updateClient( id,  obj: {}): Observable<any> {
    const url = `${this.API}clients/${id}`;
    return this.http.put(url, obj).map(res => res.json());
  }

  getProducts(): Observable<Product[]> {
    const url = `${this.API}products`;
    return this.http.get(url)
      .map(res => res.json());
  }

  findProductById(id): Observable<Product> {
    const url = `${this.API}products/${id}`;
    return this.http.get(url)
      .map(res => res.json());
  }

  addProduct(obj: {}): Observable<Response> {
    const url = `${this.API}products`;
    return this.http.post(url, obj);
  }

  deleteProductById(id) {
    const url = `${this.API}products/${id}`;
    return this.http.delete(url);
  }

  updateProduct( id,  obj: {}): Observable<any> {
    const url = `${this.API}products/${id}`;
    return this.http.put(url, obj).map(res => res.json());
  }


  getCurrentTypes(): Observable<any> {
    const url = `${this.API}products/currency`;
    console.log(url);
    return this.http.get(url).map(res => res.json());
  }

}
