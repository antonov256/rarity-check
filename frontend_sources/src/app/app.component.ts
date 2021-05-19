import { LoaderService } from "./services/loader.service";
import { Component, ViewChild, OnInit, AfterViewInit } from "@angular/core";
import { MatDrawer } from "@angular/material/sidenav";

import { SessionService } from "./services/session.service";
import { DrawerService } from "./services/drawer.service";
import { CategoriesService } from "./services/api/categories.service";

import { ICategory } from "./models";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent implements OnInit, AfterViewInit {
  @ViewChild("catDrawer") public drawer: MatDrawer;
  categories: ICategory[] = [];

  constructor(public drawerService: DrawerService, private categoriesService: CategoriesService) {}

  async ngOnInit() {
    this.categories = await this.categoriesService.getAll();
  }

  ngAfterViewInit() {
    this.drawerService.setSidenav(this.drawer);
  }
}
