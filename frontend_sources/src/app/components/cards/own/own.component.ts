import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";

import { DialogService } from "src/app/services/dialog.service";
import { NotificationsService } from "src/app/services/notification.service";
import { UserStorageService } from "src/app/services/user-storage.service";

import { IOwnItem } from "src/app/models";

@Component({
  selector: "app-card-own",
  templateUrl: "./own.component.html",
  styleUrls: ["./own.component.scss"],
})
export class OwnComponent implements OnInit {
  @Input() ownItem: IOwnItem;
  @Output() itemDeleted = new EventEmitter();

  previewImage = "assets/img/item_image_placeholder.png";

  constructor(
    private userStorageService: UserStorageService,
    private dialogService: DialogService,
    private notificationsService: NotificationsService
  ) {}

  ngOnInit(): void {
    if (this.ownItem.item.photos.length) {
      this.previewImage = this.ownItem.item.photos[0].url;
    }
  }

  async confirmDeleteAction() {
    const response = await this.dialogService.showConfirm(`Remove ${this.ownItem.item.title} from own list?`);

    if (response) {
      await this.userStorageService.removeFromOwnList(this.ownItem.id);
      this.itemDeleted.emit(this.ownItem.id);
      // TODO: fix after back (notifications)
      this.notificationsService.openSnackBar("Item removed from Own list");
    }
  }
}
