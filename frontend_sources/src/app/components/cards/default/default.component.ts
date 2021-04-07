import { Component, Input, OnInit } from '@angular/core';

import { IItem } from 'src/app/models';

@Component({
  selector: 'app-card-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.scss']
})
export class DefaultCardComponent implements OnInit {
  @Input() item: IItem;

  constructor() { }

  ngOnInit(): void {}

}
