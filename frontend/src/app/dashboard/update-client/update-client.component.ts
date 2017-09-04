import {Component, OnDestroy, OnInit} from '@angular/core';
import {Client} from '../../common/models/client';
import {Subscription} from 'rxjs/Subscription';
import {FormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpService} from '../../common/services/http.service';
import {MdSnackBar} from '@angular/material';

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.scss']
})
export class UpdateClientComponent implements OnInit, OnDestroy {

  client: Client;

  oldClient: Client;

  subscriptions: Subscription[] = [];

  updateClientForm = this.fb.group({
    firstName: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(56)]],
    lastName: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(56)]]
  });

  constructor(private fb: FormBuilder,
              private httpService: HttpService,
              private route: ActivatedRoute,
              private  router: Router,
              private snackBar: MdSnackBar) {
    this.findClientById(this.route.snapshot.params.id);
  }

  ngOnInit() {
    this.httpService.findClientById(this.route.snapshot.params.id).subscribe(res => this.client = res);
  }

  updateClient() {
    this.httpService.updateClient(this.route.snapshot.params.id, this.updateClientForm.value).subscribe(
      res => this.success(),
      res => this.failure());
  }

  findClientById(id) {
    const findClientSubscription = this.httpService.findClientById(id)
      .subscribe(
        res => {
          this.oldClient = res;
        }
      );
    this.subscriptions.push(findClientSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }

  clearForm() {
    this.updateClientForm.reset();
  }

  clientUpdatePopup(message: string) {
    this.snackBar.open(message, '', {
      duration: 2000,
    });
  }

  private success() {
    this.clientUpdatePopup('Client updated');
    this.clearForm();
  }

  private failure() {
    this.clientUpdatePopup('Error');
  }
}
