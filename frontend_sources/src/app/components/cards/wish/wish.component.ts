import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";

import { NotificationsService } from "src/app/services/notification.service";
import { DialogService } from "src/app/services/dialog.service";
import { UserStorageService } from "src/app/services/user-storage.service";

import { IWishItem } from "src/app/models/iternal/IWishItem";

@Component({
  selector: "app-card-wish",
  templateUrl: "./wish.component.html",
  styleUrls: ["./wish.component.scss"],
})
export class WishComponent implements OnInit {
  @Input() wishItem: IWishItem;

  @Output() itemDeleted = new EventEmitter();

  previewImage = "src/assets/img/item-image-placeholder.png";

  constructor(
    private userStorageService: UserStorageService,
    private dialogService: DialogService,
    private notificationsService: NotificationsService
  ) {}

  ngOnInit(): void {
    if (this.wishItem.item.photos.length) {
      this.previewImage = this.wishItem.item.photos[0].url;
    }
  }

  async confirmDeleteAction() {
    const response = await this.dialogService.showConfirm(`Remove ${this.wishItem.item.title} from wish list?`);

    if (response) {
      await this.userStorageService.removeFromWishList(this.wishItem.id);
      this.itemDeleted.emit(this.wishItem.id);
      // TODO: fix after back (notifications)
      this.notificationsService.openSnackBar("Item removed from Wish list");
    }
  }
}
