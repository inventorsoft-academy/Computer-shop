import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {DashboardModule} from './dashboard/dashboard.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {MainPageComponent} from './dashboard/main-page/main-page.component';
import {ClientsListComponent} from './dashboard/clients-list/clients-list.component';
import {ClientInfoComponent} from './dashboard/client-info/client-info.component';
import {NewClientComponent} from './dashboard/new-client/new-client.component';
import {HttpService} from './common/services/http.service';
import {MATERIAL_SANITY_CHECKS} from '@angular/material';
import 'hammerjs/hammer';
import {UpdateClientComponent} from './dashboard/update-client/update-client.component';
import {ProductsListComponent} from './dashboard/products-list/products-list.component';
import {UpdateProductComponent} from './dashboard/update-product/update-product.component';
import {ProductInfoComponent} from './dashboard/product-info/product-info.component';
import {NewProductComponent} from './dashboard/new-product/new-product.component';
import {OrderInfoComponent} from './dashboard/order-info/order-info.component';
import {OrdersListComponent} from './dashboard/orders-list/orders-list.component';
import {UpdateOrderComponent} from './dashboard/update-order/update-order.component';
import {NewOrderComponent} from './dashboard/new-order/new-order.component';


const routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: '',
        redirectTo: 'main-page',
        pathMatch: 'full'
      },
      {
        path: 'main-page',
        component: MainPageComponent
      },
      {
        path: 'new-client',
        component: NewClientComponent
      },
      {
        path: 'new-order',
        component: NewOrderComponent
      },
      {
        path: 'new-product',
        component: NewProductComponent
      },

      {
        path: 'update-order/:id',
        component: UpdateOrderComponent
      },
      {
        path: 'update-client/:id',
        component: UpdateClientComponent
      },
      {
        path: 'update-product/:id',
        component: UpdateProductComponent
      },
      {
        path: 'client-info/:id',
        component: ClientInfoComponent
      },
      {
        path: 'product-info/:id',
        component: ProductInfoComponent
      },

      {
        path: 'order-info/:id',
        component: OrderInfoComponent
      },

      {
        path: 'clients-list',
        component: ClientsListComponent
      },
      {
        path: 'orders-list',
        component: OrdersListComponent
      },
      {
        path: 'products-list',
        component: ProductsListComponent
      },
    ]
  },
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    DashboardModule,
    RouterModule.forRoot(routes)
  ],
  providers: [HttpService, {
    provide: MATERIAL_SANITY_CHECKS,
    useValue: false
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
