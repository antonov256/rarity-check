import { Component, OnInit } from "@angular/core";

import { IOwnItem } from './../../models/iternal/IOwnItem';
import { IWishItem } from './../../models/iternal/IWishItem';

@Component({
  selector: "app-portfolio",
  templateUrl: "./portfolio.component.html",
  styleUrls: ["./portfolio.component.scss"],
})
export class PortfolioComponent implements OnInit {
  constructor() {}

  mocCardConstructor(id: number): any{
    return {
      addDate: new Date(),
      id,
      item: {
        id: id,
        title: 'Coin',
        description: 'megasupercoin',
        category: '',
        subcategory: '',
        qualityValue: 1,
        photos: [
          './../../../assets/img/placeimg_640_480_tech.jpg'
        ],
      }
    }
  }

  wishItems: IWishItem[];
  ownItems: IOwnItem[];

  ngOnInit(): void {
    this.wishItems = [
      this.mocCardConstructor(1),
      this.mocCardConstructor(2),
      this.mocCardConstructor(3),
      this.mocCardConstructor(4),
      this.mocCardConstructor(5),
    ];

    this.ownItems = [
      this.mocCardConstructor(1),
      this.mocCardConstructor(2),
      this.mocCardConstructor(3),
      this.mocCardConstructor(4),
      this.mocCardConstructor(5),
    ];
  }
}
