import { Component, Input, OnInit } from "@angular/core";

import { IItem } from "src/app/models";

@Component({
  selector: "app-card-default",
  templateUrl: "./default.component.html",
  styleUrls: ["./default.component.scss"],
})
export class DefaultCardComponent implements OnInit {
  @Input() item: IItem;
  descriptionLength = 115;

  previewImage = "assets/img/item_image_placeholder.png";

  constructor() {}

  ngOnInit(): void {
    const trimmedDescription =
      this.item.description.length > this.descriptionLength + 3
        ? this.item.description.slice(0, this.descriptionLength) + "..."
        : this.item.description;

    this.item = {
      ...this.item,
      description: trimmedDescription,
    };

    if (this.item.photos.length) {
      this.previewImage = this.item.photos[0].url;
    }
  }
}
