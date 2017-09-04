import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpService} from '../../common/services/http.service';
import {Subscription} from 'rxjs/Subscription';
import {Router} from '@angular/router';
import {Client} from '../../common/models/client';

@Component({
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit, OnDestroy {

  clientsList: Client[];

  subscriptions: Subscription[] = [];

  constructor(private httpService: HttpService,
              private router: Router) {
  }

  ngOnInit() {
    this.getClients();
  }

  getClients() {
    const getClientsSubscription = this.httpService.getClients()
      .subscribe(
        res => {
          this.clientsList = res;
        }
      );
    this.subscriptions.push(getClientsSubscription);
  }

  showClientInfo(id) {
    this.router.navigate([`dashboard/client-info/${id}`]);
  }

  deleteClientById(id) {
    const deleteClientSub = this.httpService
      .deleteClientById(id).subscribe(res => {
        this.getClients();
      });
    this.subscriptions.push(deleteClientSub);
  }

  updateClientGet(id) {
    this.router.navigate([`dashboard/update-client/${id}`]);
  }


  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    });
  }
}
