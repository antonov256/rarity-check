import { Component, OnInit } from "@angular/core";

import { CategoriesService } from "src/app/services/api/categories.service";
import { ItemsService } from "src/app/services/api/items.service";

@Component({
  selector: "app-footer",
  templateUrl: "./footer.component.html",
  styleUrls: ["./footer.component.scss"],
})
export class FooterComponent implements OnInit {
  categoriesToShow: any[] = [];

  constructor(private categoriesService: CategoriesService, private itemsService: ItemsService) {}

  async ngOnInit(): Promise<void> {
    // TODO: Uncomment it to get items in footer
    // const categories = await this.categoriesService.getAll();
    // const items = await this.itemsService.getAll();
    // this.categoriesToShow = categories.map((category) => ({
    //   title: category.name,
    //   items: [...items.filter((item) => item.classification.category.id === category.id)].slice(0, 5),
    // }));
    // this.categoriesToShow = this.categoriesToShow.filter((category) => category.items.length > 3);
    // this.categoriesToShow = this.categoriesToShow.sort((a, b) => a.items.length - b.items.length);
    // this.categoriesToShow = this.categoriesToShow.splice(0, 2);
  }
}
