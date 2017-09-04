import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {HttpService} from "../../common/services/http.service";
import {Router} from "@angular/router";
import {Product} from "../../common/models/product";

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.scss']
})
export class ProductsListComponent implements OnInit, OnDestroy {

  productsList: Product[];

  subscriptions: Subscription[] = [];

  constructor(private httpService: HttpService,
              private router: Router) {
  }

  ngOnInit() {
    this.getProducts();
  }

  getProducts() {
    const getProductsSubscription = this.httpService.getProducts()
      .subscribe(
        res => {
          this.productsList = res;
        }
      );
    this.subscriptions.push(getProductsSubscription);
  }

  showProductInfo(id) {
    this.router.navigate([`dashboard/product-info/${id}`]);
  }

  deleteProductById(id) {
    const deleteProductSub = this.httpService
      .deleteProductById(id).subscribe(res => {
        this.getProducts();
      });
    this.subscriptions.push(deleteProductSub);
  }

  updateProductGet(id) {
    this.router.navigate([`dashboard/update-product/${id}`]);
  }


  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    });
  }
}
