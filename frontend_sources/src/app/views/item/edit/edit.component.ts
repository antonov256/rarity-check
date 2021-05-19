import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-edit",
  templateUrl: "./edit.component.html",
  styleUrls: ["./edit.component.scss"],
})
export class ItemEditComponent implements OnInit {
  itemId: number;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.itemId = +this.activatedRoute.snapshot.paramMap.get("id");
  }
}
