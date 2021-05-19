import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit } from "@angular/core";
import { PageEvent } from "@angular/material/paginator";

import { ItemsService } from "src/app/services/api/items.service";

import { IItem, IWishItem, IOwnItem, IAPIParams } from "src/app/models";
import { Subject, Subscription } from "rxjs";

@Component({
  selector: "app-paged-cards",
  templateUrl: "./paged-cards.component.html",
  styleUrls: ["./paged-cards.component.scss"],
})
export class PagedCardsComponent implements OnInit, OnDestroy {
  @Input() items: any[] = [];
  @Input() title: string;
  @Input() pageSize = 4;
  @Input() type: "default" | "wish" | "own" = "default";
  @Input() categoryId?: number;
  @Input() subcategoryId?: number;
  @Input() searchParams?: IAPIParams;
  @Input() onFilterChanged?: Subject<IAPIParams>;

  changesSubscription: Subscription;

  actualPage = 1;
  actualItems: IItem[] | IWishItem[] | IOwnItem[] = [];
  actualLength: number;
  totalPages: number;
  pagesStore: any[] = [];

  constructor(private itemsService: ItemsService) {}

  async ngOnInit(): Promise<void> {
    this.changesSubscription = this.onFilterChanged?.subscribe(async (searchParams: IAPIParams) => {
      this.searchParams = searchParams;
      this.pagesStore = [];
      await this.generateActualItems();
    });

    await this.generateActualItems();
  }

  ngOnDestroy() {
    this.changesSubscription?.unsubscribe();
  }

  public get pageIndex() {
    return this.actualPage - 1;
  }

  async generateActualItems(): Promise<void> {
    if (this.categoryId?.toString()) {
      await this.generateActualItemsByCategory();
      return;
    }

    if (this.subcategoryId?.toString()) {
      await this.generateActualItemsBySubcategory();
      return;
    }

    if (this.searchParams) {
      await this.generateActualItemsBySearch();
      return;
    }

    this.actualItems = this.items.filter((item, index) => {
      const currentLastItemIndex = this.actualPage * this.pageSize;
      if (index >= currentLastItemIndex - this.pageSize && index < currentLastItemIndex) {
        return true;
      }
      return false;
    });
  }

  async generateActualItemsByCategory(): Promise<void> {
    if (!this.pagesStore[this.pageIndex]?.length) {
      let response;
      if (this.type === "default") {
        response = await this.itemsService.getItems({
          categoryId: this.categoryId,
          pageSize: this.pageSize,
          pageNumber: this.pageIndex,
        });
      }

      this.pagesStore.push(response._embedded.items);
      this.actualLength = response.page.totalElements;
      this.totalPages = response.page.totalPages;
    }

    if (!this.pagesStore[this.pageIndex + 1]?.length && this.pageIndex + 1 < this.totalPages) {
      let response;
      if (this.type === "default") {
        response = await this.itemsService.getItems({
          categoryId: this.categoryId,
          pageSize: this.pageSize,
          pageNumber: this.pageIndex + 1,
        });
      }

      this.pagesStore.push(response._embedded.items);
    }

    this.actualItems = this.pagesStore[this.pageIndex];
  }

  async generateActualItemsBySubcategory(): Promise<void> {
    if (!this.pagesStore[this.pageIndex]?.length) {
      let response;
      if (this.type === "default") {
        response = await this.itemsService.getItems({
          subcategoryId: this.subcategoryId,
          pageSize: this.pageSize,
          pageNumber: this.pageIndex,
        });
      }

      this.pagesStore.push(response._embedded.items);
      this.actualLength = response.page.totalElements;
      this.totalPages = response.page.totalPages;
    }

    if (!this.pagesStore[this.pageIndex + 1]?.length && this.pageIndex + 1 < this.totalPages) {
      let response;
      if (this.type === "default") {
        response = await this.itemsService.getItems({
          subcategoryId: this.subcategoryId,
          pageSize: this.pageSize,
          pageNumber: this.pageIndex + 1,
        });
      }

      this.pagesStore.push(response._embedded.items);
    }

    this.actualItems = this.pagesStore[this.pageIndex];
  }

  async generateActualItemsBySearch(): Promise<void> {
    if (!this.pagesStore[this.pageIndex]?.length) {
      let response;
      if (this.type === "default") {
        response = await this.itemsService.getItems({
          ...this.searchParams,
          pageSize: this.pageSize,
          pageNumber: this.pageIndex,
        });
      }

      this.pagesStore.push(response._embedded.items);
      this.actualLength = response.page.totalElements;
      this.totalPages = response.page.totalPages;
    }

    if (!this.pagesStore[this.pageIndex + 1]?.length && this.pageIndex + 1 < this.totalPages) {
      let response;
      if (this.type === "default") {
        response = await this.itemsService.getItems({
          ...this.searchParams,
          pageSize: this.pageSize,
          pageNumber: this.pageIndex + 1,
        });
      }

      this.pagesStore.push(response._embedded.items);
    }

    this.actualItems = this.pagesStore[this.pageIndex];
  }

  async onPageEvent(e: PageEvent): Promise<void> {
    this.actualPage = e.pageIndex + 1;
    await this.generateActualItems();
  }

  removeItem(itemId: number): void {
    this.items = this.items.filter((item) => item.id !== itemId);
    this.generateActualItems();
  }
}
