import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';

import {HeaderComponent} from './header/header.component';
import {MainPageComponent} from './main-page/main-page.component';
import {NewClientComponent} from './new-client/new-client.component';
import {ClientsListComponent} from './clients-list/clients-list.component';
import {ClientInfoComponent} from './client-info/client-info.component';
import {DashboardComponent} from './dashboard.component';
import {MaterialModule} from "../material.module";
import {OrdersListComponent} from "./orders-list/orders-list.component";
import {OrderInfoComponent} from "./order-info/order-info.component";
import {ProductsListComponent} from "./products-list/products-list.component";
import {ProductInfoComponent} from "./product-info/product-info.component";
import {NewProductComponent} from "./new-product/new-product.component";
import {NewOrderComponent} from "./new-order/new-order.component";
import {UpdateOrderComponent} from "./update-order/update-order.component";
import {UpdateClientComponent} from "./update-client/update-client.component";
import {UpdateProductComponent} from "./update-product/update-product.component";


@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    RouterModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  declarations: [
    HeaderComponent,
    MainPageComponent,
    NewClientComponent,
    ClientsListComponent,
    ClientInfoComponent,
    DashboardComponent,
    NewProductComponent,
    ProductInfoComponent,
    ProductsListComponent,
    OrderInfoComponent,
    OrdersListComponent,
    NewOrderComponent,
    UpdateOrderComponent,
    UpdateClientComponent,
    UpdateProductComponent
  ],
  providers: []
})
export class DashboardModule {
}
