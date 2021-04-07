import { Component, Input, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';

import { IWishItem } from 'src/app/models';

@Component({
  selector: 'app-paged-wish-cards',
  templateUrl: './paged-wish-cards.component.html',
  styleUrls: ['./paged-wish-cards.component.scss']
})
export class PagedWishCardsComponent implements OnInit {
  @Input() items: any[];
  @Input() title: string;

  actualPage: number = 1;
  actualItems: IWishItem[] = [];

  ngOnInit(): void{
    this.generateActualItems();
  }

  generateActualItems(){
    this.actualItems = this.items.filter((item, index) => {
      const currentLastItemIndex = this.actualPage * 4;
      if(index >= currentLastItemIndex - 4 && index < currentLastItemIndex){
        return true;
      }
      return false;
    });
  }

  onPageEvent(e: PageEvent){
    this.actualPage = e.pageIndex + 1;
    this.generateActualItems();
  }
}
