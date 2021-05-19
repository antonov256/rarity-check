import { Injectable } from "@angular/core";

import { HttpRequestsService } from "../http-requests.service";
import { SessionService } from "../session.service";

import { IAPIResponsePaged, IAPIItem, IItem, IAPIParams } from "src/app/models";

import { ITEMS_ROUTE } from "src/environments/api.routes";
import { ITEMS_UPDATE_INTERVAL } from "src/environments/api.pops";
import { getApiParamsString } from "src/app/utils/methods/getApiParamsString";

@Injectable({
  providedIn: "root",
})
export class ItemsService {
  lastUpdate = 0;
  storage: IAPIItem[] = [];

  constructor(private httpRequestService: HttpRequestsService, private sessionService: SessionService) {}

  create(data: IItem): Promise<IAPIItem> {
    return this.httpRequestService.post(ITEMS_ROUTE, data);
  }

  async getAll(): Promise<IAPIItem[]> {
    const currentTime = Date.now();

    if (this.lastUpdate + ITEMS_UPDATE_INTERVAL < currentTime || this.sessionService.isAdmin) {
      const response = await this.httpRequestService.get<IAPIResponsePaged>(ITEMS_ROUTE + "?size=200");

      this.lastUpdate = currentTime;
      this.storage = response._embedded.items;
    }

    return this.storage;
  }

  async getItems(params: IAPIParams): Promise<any> {
    const paramsString = getApiParamsString(params);
    // TODO: fix after backend
    return this.httpRequestService.get<IAPIResponsePaged>(ITEMS_ROUTE + paramsString);
  }

  getOne(id: number): Promise<IAPIItem> {
    return this.httpRequestService.get(ITEMS_ROUTE + "/" + id);
  }

  update(id: number, data: IItem): Promise<IAPIItem> {
    return this.httpRequestService.put(ITEMS_ROUTE + "/" + id, data);
  }

  delete(id: number): Promise<IAPIItem> {
    return this.httpRequestService.delete(ITEMS_ROUTE + "/" + id);
  }
}
