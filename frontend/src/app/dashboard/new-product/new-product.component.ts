import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Subscription} from "rxjs/Subscription";
import {HttpService} from "../../common/services/http.service";
import {MdSnackBar} from "@angular/material";

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.scss']
})
export class NewProductComponent implements OnDestroy, OnInit {
  currencyTypeList: any[];
  subscriptions: Subscription[] = [];

  newProductForm = this.fb.group({
    currency: ['', Validators.required],
    name: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(56)]],
    price: ['', [Validators.required, Validators.min(5.0), Validators.max(500000.0)]]
  });

  constructor(private fb: FormBuilder, private httpService: HttpService, private snackBar: MdSnackBar) {
  }

  ngOnInit() {
    this.getCurrentTypes();
  }

  getCurrentTypes() {
    const getCurrentSubscription = this.httpService.getCurrentTypes()
      .subscribe(
        res => {
          this.currencyTypeList = res;
        }
      );
    this.subscriptions.push(getCurrentSubscription);
  }

  addProduct() {
    const addProductSubs = this.httpService.addProduct(this.newProductForm.value).subscribe(
      res => this.success(),
      res => this.failure());
    this.subscriptions.push(addProductSubs);
    this.clearForm();
  }

  clearForm() {
    this.newProductForm.reset();
  }

  productSavedPopup(message: string) {
    this.snackBar.open(message, '', {
      duration: 2000,
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      });
  }

  private success() {
    this.productSavedPopup('Product saved');
    this.clearForm();
  }

  private failure() {
    this.productSavedPopup('Error');
  }
}
