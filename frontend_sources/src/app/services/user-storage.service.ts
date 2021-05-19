import { Injectable } from "@angular/core";
import { IOwnItem, IWishItem } from "../models";
import { OwnService } from "./api/own.service";
import { WishService } from "./api/wish.service";

@Injectable({
  providedIn: "root",
})
export class UserStorageService {
  _wishList?: IWishItem[];
  _ownList?: IOwnItem[];

  constructor(private wishService: WishService, private ownService: OwnService) {}

  async init(): Promise<void> {
    this._wishList = await this.wishService.getList();
    this._ownList = await this.ownService.getList();
  }

  destroy(): void {
    this._wishList = [];
    this._ownList = [];
  }

  async removeFromWishList(itemId: number): Promise<void> {
    await this.wishService.remove(itemId);
    this.wishList.splice(
      this.wishList.findIndex((item) => item.id === itemId),
      1
    );
  }

  async removeFromOwnList(itemId: number): Promise<void> {
    await this.ownService.remove(itemId);
    this.ownList.splice(
      this.ownList.findIndex((item) => item.id === itemId),
      1
    );
  }

  async addToWishList(itemId: number): Promise<IWishItem> {
    const response = await this.wishService.add(itemId);
    this.wishList.push(response);
    return response;
  }

  async addToOwnList(itemId: number): Promise<IOwnItem> {
    const response = await this.ownService.add(itemId);
    this.ownList.push(response);
    return response;
  }

  get wishList(): IWishItem[] {
    return this._wishList;
  }

  get ownList(): IOwnItem[] {
    return this._ownList;
  }
}
