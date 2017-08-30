import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

import { HeaderComponent } from './header/header.component';
import { MainPageComponent } from './main-page/main-page.component';
import { NewClientComponent } from './new-client/new-client.component';
import { ClientsListComponent } from './clients-list/clients-list.component';
import { ClientInfoComponent } from './client-info/client-info.component';
import { DashboardComponent } from './dashboard.component';

@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    RouterModule,
    ReactiveFormsModule
  ],
  declarations: [
    HeaderComponent,
    MainPageComponent,
    NewClientComponent,
    ClientsListComponent,
    ClientInfoComponent,
    DashboardComponent
  ],
  providers: []
})
export class DashboardModule {
}
