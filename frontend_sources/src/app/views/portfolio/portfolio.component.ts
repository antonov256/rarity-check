import { Component, OnInit } from "@angular/core";

import { UserStorageService } from "src/app/services/user-storage.service";

@Component({
  selector: "app-portfolio",
  templateUrl: "./portfolio.component.html",
  styleUrls: ["./portfolio.component.scss"],
})
export class PortfolioComponent implements OnInit {
  constructor(public userStorageService: UserStorageService) {}

  async ngOnInit(): Promise<void> {}
}
