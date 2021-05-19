import { Injectable } from "@angular/core";
import { SessionService } from "./session.service";

@Injectable({
  providedIn: "root",
})
export class AppInitService {
  constructor(private sessionService: SessionService) {}

  init() {
    return new Promise((resolve, reject) => {
      this.sessionService.init().then(resolve);
    });
  }
}
