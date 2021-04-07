import { Component, Input, OnInit, Output, ViewChild, EventEmitter } from '@angular/core';
import { MatAccordion } from '@angular/material/expansion';

@Component({
  selector: 'app-category-tree',
  templateUrl: './category-tree.component.html',
  styleUrls: ['./category-tree.component.scss']
})
export class CategoryTreeComponent implements OnInit {

  @Input() categories: any;
  @Output() listClick: any = new EventEmitter();

  @ViewChild(MatAccordion) accordion: MatAccordion;

  constructor() {
    this.categories = [
      this.mocCategory(),
      this.mocCategory(),
      this.mocCategory(),
      this.mocCategory(),
      this.mocCategory(),
      this.mocCategory(),
    ]
  }

  mocCategory(name = 'America and Commonwealth', description = 'Items from America and Commonwealth'){
    return {
      title: name,
      description: description,
      items: [
        {
          title: 'USA'
        },
        {
          title: 'United Kingdom'
        },
        {
          title: 'Mexico'
        },
        {
          title: 'Canada'
        },
        {
          title: 'Caribbean, Central America'
        }
      ]
    }
  }

  ngOnInit(): void {
  }

  emitClick(event: any){
    this.listClick.emit(event);
  }
}
