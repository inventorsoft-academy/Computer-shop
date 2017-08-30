import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MainPageComponent } from './dashboard/main-page/main-page.component';
import { ClientsListComponent } from './dashboard/clients-list/clients-list.component';
import { ClientInfoComponent } from './dashboard/client-info/client-info.component';
import { NewClientComponent } from './dashboard/new-client/new-client.component';
import { HttpService } from './common/services/http.service';

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
        path: 'clients-list',
        component: ClientsListComponent
      },
      {
        path: 'client-info/:id',
        component: ClientInfoComponent
      },
      {
        path: 'new-client',
        component: NewClientComponent
      }
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
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
