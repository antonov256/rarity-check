import { Component, Input, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';

import { IOwnItem } from '../../../models/iternal/IOwnItem';

@Component({
  selector: 'app-paged-own-cards',
  templateUrl: './paged-own-cards.component.html',
  styleUrls: ['./paged-own-cards.component.scss']
})
export class PagedOwnCardsComponent implements OnInit {
  @Input() items: any[];
  @Input() title: string;

  actualPage: number = 1;
  actualItems: IOwnItem[] = [];

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
