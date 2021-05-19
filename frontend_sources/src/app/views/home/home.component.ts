import { Component, OnInit } from "@angular/core";
import { Subject } from "rxjs";

import { CategoriesService } from "src/app/services/api/categories.service";
import { ItemsService } from "src/app/services/api/items.service";
import { DrawerService } from "src/app/services/drawer.service";
import { LoaderService } from "src/app/services/loader.service";

import { IItem, ICategory, IAPIParams } from "src/app/models";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  constructor(
    public drawerService: DrawerService,
    public categoriesService: CategoriesService,
    private itemsService: ItemsService,
    public loaderService: LoaderService
  ) {}
  categories: ICategory[] = [];
  items: IItem[] = [];
  sections: any[] = [];

  filterChanged = new Subject<IAPIParams>();

  isSearch = false;
  searchString = "";
  searchCategory?: number;
  searchPageSize = 20;
  searchParams: IAPIParams;

  searchedItems: IItem[] = [];

  checkIsSearch() {
    return this.isSearch;
  }

  async ngOnInit(): Promise<void> {
    this.categories = await this.categoriesService.getNotEmpty();
    // TODO: Uncomment it to get non empty categories
    // this.items = await this.itemsService.getAll();

    this.sections = this.categories.map((category) => ({
      id: category.id,
      title: category.name,
      items: [...this.items.filter((item) => item.classification.category.id === category.id)],
    }));

    // TODO: Uncomment it to get non empty categories
    // this.sections = this.sections.filter((category) => category.items.length);
  }

  searchItems(): void {
    this.searchParams = {
      pageSize: this.searchPageSize,
    };

    if (this.searchString) {
      this.searchParams.title = this.searchString;
    }

    if (this.searchCategory?.toString() || this.searchCategory == null) {
      this.searchParams.categoryId = this.searchCategory;
    }

    this.isSearch = true;
    this.filterChanged.next(this.searchParams);
  }
}
