import { Component, OnInit } from "@angular/core";

import { SessionService } from "../../services/session.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.scss"],
})
export class HeaderComponent implements OnInit {
  constructor(public sessionService: SessionService) {}

  ngOnInit(): void {}

  logOut() {
    this.sessionService.logOut();
  }

  scrollTo() {
    document.getElementById("content").scrollTo({
      top: 100000,
      behavior: "smooth",
    });
  }
}
