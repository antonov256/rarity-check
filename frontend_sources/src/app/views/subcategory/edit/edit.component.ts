import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-edit",
  templateUrl: "./edit.component.html",
  styleUrls: ["./edit.component.scss"],
})
export class SubcategoryEditComponent implements OnInit {
  entityId: number;

  constructor(private activatedRoute: ActivatedRoute) {
    this.entityId = +this.activatedRoute.snapshot.paramMap.get("id");
  }

  ngOnInit(): void {}
}
