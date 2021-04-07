import { Component, Input, OnInit } from '@angular/core';

import { IWishItem } from './../../../models/iternal/IWishItem';

@Component({
  selector: 'app-card-wish',
  templateUrl: './wish.component.html',
  styleUrls: ['./wish.component.scss']
})
export class WishComponent implements OnInit {

  @Input() wishItem: IWishItem;

  constructor() {}

  ngOnInit(): void {
  }

  deleteHandler(){

  }

}
