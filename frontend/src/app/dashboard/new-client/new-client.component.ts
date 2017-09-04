import {Component, OnDestroy} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {HttpService} from '../../common/services/http.service';
import {Subscription} from 'rxjs/Subscription';
import {MdSnackBar} from '@angular/material';

@Component({
  templateUrl: './new-client.component.html'
})
export class NewClientComponent implements OnDestroy {

  newClientForm = this.fb.group({
    firstName: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(56)]],
    lastName: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(56)]]
  });

  subscriptions: Subscription[] = [];

  constructor(private fb: FormBuilder,
              private httpService: HttpService,
              private snackBar: MdSnackBar) {
  }

  addClient() {
    const addClientSubs = this.httpService.addClient(this.newClientForm.value).subscribe(
      res => this.success(),
      res => this.failure());
    this.subscriptions.push(addClientSubs);
    this.clearForm();
  }

  clearForm() {
    this.newClientForm.reset();
  }

  clientSavedPopup(message: string) {
    this.snackBar.open(message, '', {
      duration: 2000,


    });
  }


  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }

  private success() {
    this.clientSavedPopup('Client saved');
    this.clearForm();
  }

  private failure() {
    this.clientSavedPopup('Error');
  }
}
