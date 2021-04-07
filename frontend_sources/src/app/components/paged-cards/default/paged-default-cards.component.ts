import { Component, Input, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';

import { IItem } from 'src/app/models';

@Component({
  selector: 'app-paged-default-cards',
  templateUrl: './paged-default-cards.component.html',
  styleUrls: ['./paged-default-cards.component.scss']
})
export class PagedDefaultCardsComponent implements OnInit {
  @Input() items: any[];
  @Input() title: string;

  actualPage: number = 1;
  actualItems: IItem[] = [];

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
