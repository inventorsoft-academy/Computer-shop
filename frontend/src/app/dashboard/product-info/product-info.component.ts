import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Product} from "../../common/models/product";
import {HttpService} from "../../common/services/http.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-info',
  templateUrl: './product-info.component.html',
  styleUrls: ['./product-info.component.scss']
})
export class ProductInfoComponent implements OnInit, OnDestroy {

  product: Product;

  subscriptions: Subscription[] = [];

  constructor(private route: ActivatedRoute,
              private httpService: HttpService) {
  }

  ngOnInit() {
    this.findProductById(this.route.snapshot.params.id);
  }

  findProductById(id) {
    const findProductSubs = this.httpService.findProductById(id)
      .subscribe(
        res => {
          this.product = res;
        }
      );
    this.subscriptions.push(findProductSubs);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => {
        subscription.unsubscribe();
      }
    );
  }
}
