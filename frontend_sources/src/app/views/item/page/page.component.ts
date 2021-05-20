import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Location } from "@angular/common";

import { ItemsService } from "src/app/services/api/items.service";
import { SessionService } from "src/app/services/session.service";
import { WishService } from "src/app/services/api/wish.service";
import { OwnService } from "src/app/services/api/own.service";

import { IItem } from "src/app/models";
import { UserStorageService } from "src/app/services/user-storage.service";

@Component({
  selector: "app-item-page",
  templateUrl: "./page.component.html",
  styleUrls: ["./page.component.scss"],
})
export class ItemPageComponent implements OnInit {
  itemId: number;
  itemData?: IItem;
  previewImage = "src/assets/img/1986gold5libertyr.jpg";

  inWishList = false;
  isWishDone = false;
  wishItemId: number;

  inOwnList = false;
  isOwnDone = false;
  ownItemId: number;

  constructor(
    private location: Location,
    public sessionService: SessionService,
    private activatedRoute: ActivatedRoute,
    private itemsService: ItemsService,
    private userStorageService: UserStorageService,
    private wishService: WishService,
    private ownService: OwnService
  ) {}

  async ngOnInit(): Promise<void> {
    this.itemId = +this.activatedRoute.snapshot.paramMap.get("id");

    try {
      const response = await this.itemsService.getOne(this.itemId);

      if (this.sessionService.isAuthroized) {
        const itemFromWishList = this.userStorageService.wishList.find((wishItem) => wishItem.item.id === this.itemId);
        const itemFromOwnList = this.userStorageService.ownList.find((ownItem) => ownItem.item.id === this.itemId);

        if (itemFromWishList) {
          this.inWishList = true;
          this.wishItemId = itemFromWishList.id;
        }

        if (itemFromOwnList) {
          this.inOwnList = true;
          this.ownItemId = itemFromOwnList.id;
        }
      }

      this.itemData = response;

      const firstPhoto = this.itemData.photos[0];
      if (firstPhoto) {
        this.previewImage = firstPhoto.url;
      }
    } catch (error) {
      this.location.back();
    }
  }

  async addToWishList() {
    const response = await this.userStorageService.addToWishList(this.itemId);
    this.inWishList = true;
    this.isWishDone = true;
    this.wishItemId = response.id;
  }

  async addToOwnList() {
    const response = await this.userStorageService.addToOwnList(this.itemId);
    this.inOwnList = true;
    this.isOwnDone = true;
    this.ownItemId = response.id;
  }

  async removeFromWishList() {
    await this.userStorageService.removeFromWishList(this.wishItemId);
    this.inWishList = false;
    this.isWishDone = true;
  }

  async removeFromOwnList() {
    await this.userStorageService.removeFromOwnList(this.ownItemId);
    this.inOwnList = false;
    this.isOwnDone = true;
  }

  toggleDone(type: string): void {
    switch (type) {
      case "wish":
        this.isWishDone = false;
        break;

      case "own":
        this.isOwnDone = false;
        break;
    }
  }
}
