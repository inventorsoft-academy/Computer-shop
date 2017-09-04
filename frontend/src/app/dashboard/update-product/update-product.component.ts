import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import {FormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpService} from '../../common/services/http.service';
import {Product} from "../../common/models/product";
import {MdSnackBar} from "@angular/material";


@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.scss']
})
export class UpdateProductComponent implements OnInit, OnDestroy {

  product: Product;

  currencyTypeList: any[];

  oldProduct: Product;

  subscriptions: Subscription[] = [];

  updateProductForm = this.fb.group({
    currency: ['', Validators.required],
    name: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(56)]],
    price: ['', [Validators.required, Validators.min(5.0), Validators.max(500000.0)]]
  });

  constructor(private fb: FormBuilder,
              private httpService: HttpService,
              private route: ActivatedRoute,
              private  router: Router,
              private snackBar: MdSnackBar) {
    this.findProductById(this.route.snapshot.params.id);
  }

  ngOnInit() {
    this.getCurrentTypes();
    this.httpService.findProductById(this.route.snapshot.params.id).subscribe(res => this.product = res);
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
  updateProduct() {
    this.httpService.updateProduct(this.route.snapshot.params.id, this.updateProductForm.value).subscribe(
      res => this.success(),
      res => this.failure());
  }

  findProductById(id) {
    const findProductSubscription = this.httpService.findProductById(id)
      .subscribe(
        res => {
          this.oldProduct = res;
        }
      );
    this.subscriptions.push(findProductSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }

  clearForm() {
    this.updateProductForm.reset();
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
