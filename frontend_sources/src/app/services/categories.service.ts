import { Injectable } from "@angular/core";
import { MatDrawer } from "@angular/material/sidenav";

@Injectable({
  providedIn: "root",
})
export class CategoriesService {
  private drawer: MatDrawer;

  public setSidenav(drawer: MatDrawer) {
    console.log(drawer);
    this.drawer = drawer;
  }

  public open() {
    return this.drawer.open();
  }

  public close() {
    return this.drawer.close();
  }

  public toggle(): void {
    this.drawer.toggle();
  }
}
