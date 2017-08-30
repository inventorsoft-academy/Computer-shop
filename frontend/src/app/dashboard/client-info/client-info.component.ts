import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from '../../common/services/http.service';
import { Client } from '../../common/models/client';
import { Subscription } from 'rxjs/Subscription';

@Component({
  templateUrl: './client-info.component.html'
})
export class ClientInfoComponent implements OnInit, OnDestroy {

  client: Client;

  subscriptions: Subscription[] = [];

  constructor(private route: ActivatedRoute,
              private httpService: HttpService) {
  }

  ngOnInit() {
    this.findClientById(this.route.snapshot.params.id);
  }

  findClientById(id) {
    const findClientSubs = this.httpService.findClientById(id)
      .subscribe(
        res => {
          this.client = res;
        }
      );
    this.subscriptions.push(findClientSubs);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }
}
