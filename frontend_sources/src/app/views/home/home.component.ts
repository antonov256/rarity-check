import { Component, OnInit } from "@angular/core";

import { CategoriesService } from "./../../services/categories.service";
import { IItem } from 'src/app/models';


@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  constructor(public _categoriesService: CategoriesService) {}
  categories: any[] = [
    { value: "steak-0", viewValue: "Steak" },
    { value: "pizza-1", viewValue: "Pizza" },
    { value: "tacos-2", viewValue: "Tacos" },
  ];

  sections: any[] = [
    {
      title: 'Special offer',
      items: []
    },
    {
      title: 'Abbasids',
      items: []
    }
  ]

  mocCardConstructor(id: number) : IItem{
    return {
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



  ngOnInit(): void{
    this.sections[0].items.push(
      this.mocCardConstructor(1),
      this.mocCardConstructor(2),
      this.mocCardConstructor(3),
      this.mocCardConstructor(4),
      this.mocCardConstructor(5),
      this.mocCardConstructor(6)
    );

    this.sections[1].items.push(
      this.mocCardConstructor(1),
      this.mocCardConstructor(2),
      this.mocCardConstructor(3),
      this.mocCardConstructor(4),
      this.mocCardConstructor(5),
      this.mocCardConstructor(6)
    );
  }
}
