import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { HttpService } from '../../common/services/http.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  templateUrl: './new-client.component.html'
})
export class NewClientComponent implements OnInit, OnDestroy {

  newClientForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    money: ['', Validators.required]
  });

  subscriptions: Subscription[] = [];

  constructor(private fb: FormBuilder,
              private httpService: HttpService) {
  }

  ngOnInit() {
    console.log(this.newClientForm);
  }

  addClient() {
    const addClientSubs = this.httpService.addClient(this.newClientForm.value).subscribe();
    this.subscriptions.push(addClientSubs);
    this.clearForm();
  }

  clearForm() {
    this.newClientForm.reset();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }
}
