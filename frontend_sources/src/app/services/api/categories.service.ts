import { Injectable } from "@angular/core";

import { HttpRequestsService } from "./../http-requests.service";

import { IAPIResponsePaged, IAPICategory, ICategory } from "src/app/models";

import { CATEGORIES_ROUTE } from "src/environments/api.routes";
import { CATEGORIES_UPDATE_INTERVAL } from "src/environments/api.pops";

import { SessionService } from "../session.service";

interface Storage {
  all: IAPICategory[];
  notEmpty: IAPICategory[];
}

interface LastUpdate {
  all: number;
  notEmpty: number;
}

@Injectable({
  providedIn: "root",
})
export class CategoriesService {
  lastUpdate: LastUpdate = {
    all: 0,
    notEmpty: 0,
  };

  storage: Storage = {
    all: [],
    notEmpty: [],
  };

  constructor(private httpRequestService: HttpRequestsService, private sessionService: SessionService) {}

  create(data: ICategory): Promise<IAPICategory> {
    return this.httpRequestService.post(CATEGORIES_ROUTE, data);
  }

  async getAll(): Promise<IAPICategory[]> {
    const currentTime = Date.now();

    if (this.lastUpdate.all + CATEGORIES_UPDATE_INTERVAL < currentTime || this.sessionService.isAdmin) {
      const response = await this.httpRequestService.get<IAPIResponsePaged>(CATEGORIES_ROUTE);

      this.lastUpdate.all = currentTime;
      this.storage.all = response._embedded.category;
    }

    return this.storage.all;
  }

  async getNotEmpty(): Promise<IAPICategory[]> {
    const currentTime = Date.now();

    if (this.lastUpdate.notEmpty + CATEGORIES_UPDATE_INTERVAL < currentTime || this.sessionService.isAdmin) {
      const response = await this.httpRequestService.get<IAPIResponsePaged>(CATEGORIES_ROUTE + "?filter=notEmpty");

      this.lastUpdate.notEmpty = currentTime;
      this.storage.notEmpty = response._embedded.category;
    }

    return this.storage.notEmpty;
  }

  getOne(id: number): Promise<IAPICategory> {
    return this.httpRequestService.get(CATEGORIES_ROUTE + "/" + id);
  }

  update(id: number, data: ICategory): Promise<IAPICategory> {
    return this.httpRequestService.put(CATEGORIES_ROUTE + "/" + id, data);
  }

  delete(id: number): Promise<IAPICategory> {
    return this.httpRequestService.delete(CATEGORIES_ROUTE + "/" + id);
  }
}
