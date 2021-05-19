import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Location } from "@angular/common";

import { NotificationsService } from "./../../../services/notification.service";
import { CategoriesService } from "./../../../services/api/categories.service";

import { ICategory } from "src/app/models";

@Component({
  selector: "app-edit",
  templateUrl: "./edit.component.html",
  styleUrls: ["./edit.component.scss"],
})
export class CategoryEditComponent implements OnInit {
  entityId: number;

  constructor(private activatedRoute: ActivatedRoute) {
    this.entityId = +this.activatedRoute.snapshot.paramMap.get("id");
  }

  ngOnInit(): void {}
}
