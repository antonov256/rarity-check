import { Component, OnInit } from "@angular/core";

import { LoaderService } from "src/app/services/loader.service";

@Component({
  selector: "app-loader-screen",
  templateUrl: "./loader-screen.component.html",
  styleUrls: ["./loader-screen.component.scss"],
})
export class LoaderScreenComponent implements OnInit {
  constructor(public loaderService: LoaderService) {}

  ngOnInit() {}
}
