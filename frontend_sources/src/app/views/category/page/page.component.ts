import { Location } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { CategoriesService } from "./../../../services/api/categories.service";

@Component({
  selector: "app-category-page",
  templateUrl: "./page.component.html",
  styleUrls: ["./page.component.scss"],
})
export class CategoryPageComponent implements OnInit {
  categoryId: number;
  categoryName: string;

  constructor(
    private categoriesService: CategoriesService,
    private activatedRoute: ActivatedRoute,
    private location: Location
  ) {}

  async ngOnInit(): Promise<void> {
    try {
      this.categoryId = +this.activatedRoute.snapshot.paramMap.get("id");
      const categoryResponse = await this.categoriesService.getOne(this.categoryId);
      this.categoryName = categoryResponse.name;
    } catch (error) {
      this.location.back();
    }
  }
}
