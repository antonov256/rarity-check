import { Location } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { SubcategoriesService } from "src/app/services/api/subcategories.service";

@Component({
  selector: "app-page",
  templateUrl: "./page.component.html",
  styleUrls: ["./page.component.scss"],
})
export class SubcategoryPageComponent implements OnInit {
  subcategoryId: number;
  subcategoryName: string;

  constructor(
    private subcategoriesService: SubcategoriesService,
    private activatedRoute: ActivatedRoute,
    private location: Location
  ) {}

  async ngOnInit(): Promise<void> {
    try {
      this.subcategoryId = +this.activatedRoute.snapshot.paramMap.get("id");
      const categoryResponse = await this.subcategoriesService.getOne(this.subcategoryId);
      this.subcategoryName = categoryResponse.name;
    } catch (error) {
      this.location.back();
    }
  }
}
