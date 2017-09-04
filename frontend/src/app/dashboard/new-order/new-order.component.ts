import {Component, OnDestroy, OnInit} from '@angular/core';
import {MdSnackBar} from "@angular/material";
import {HttpService} from "../../common/services/http.service";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.scss']
})
export class NewOrderComponent {}
