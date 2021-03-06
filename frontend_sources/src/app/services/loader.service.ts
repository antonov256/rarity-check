import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class LoaderService {
  private _loading = false;

  get loading(): boolean {
    return this._loading;
  }

  set loading(value) {
    this._loading = value;
  }

  startLoading() {
    this.loading = true;
  }

  stopLoading() {
    this.loading = false;
  }
}
