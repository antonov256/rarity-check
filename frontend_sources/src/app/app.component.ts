import { AuthService } from './services/auth.service';
import { Component, ViewChild, OnInit, AfterViewInit } from "@angular/core";
import { MatDrawer } from "@angular/material/sidenav";
import { CategoriesService } from "./services/categories.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent implements AfterViewInit {
  @ViewChild("catDrawer") public drawer: MatDrawer;

  constructor(private _categoriesService: CategoriesService, private authService: AuthService) {
    this.authService.init();
  }

  ngAfterViewInit() {
    this._categoriesService.setSidenav(this.drawer);
  }
}
