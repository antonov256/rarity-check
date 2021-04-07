import { Component, Input, OnInit } from '@angular/core';

import { IOwnItem } from 'src/app/models';

@Component({
  selector: 'app-card-own',
  templateUrl: './own.component.html',
  styleUrls: ['./own.component.scss']
})
export class OwnComponent implements OnInit {

  @Input() ownItem: IOwnItem;

  constructor() {}

  ngOnInit(): void {
  }

  deleteHandler(){

  }

}
