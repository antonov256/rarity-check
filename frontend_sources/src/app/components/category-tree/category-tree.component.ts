import { SubcategoriesService } from "./../../services/api/subcategories.service";
import { Component, Input, OnInit, Output, ViewChild, EventEmitter } from "@angular/core";
import { MatAccordion } from "@angular/material/expansion";

import { CategoriesService } from "./../../services/api/categories.service";

@Component({
  selector: "app-category-tree",
  templateUrl: "./category-tree.component.html",
  styleUrls: ["./category-tree.component.scss"],
})
export class CategoryTreeComponent implements OnInit {
  categories: any;

  @ViewChild(MatAccordion) accordion: MatAccordion;

  constructor(private categoriesService: CategoriesService, private subcategoriesService: SubcategoriesService) {}

  async ngOnInit(): Promise<void> {
    this.categories = await this.categoriesService.getAll();
  }
}
