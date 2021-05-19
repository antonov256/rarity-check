import { Injectable } from "@angular/core";
import { HttpRequestsService } from "../http-requests.service";

import { IAPIResponsePaged } from "src/app/models";
import { WISH_ROUTE } from "src/environments/api.routes";

@Injectable({
  providedIn: "root",
})
export class WishService {
  // TODO: add model
  constructor(private httpRequestService: HttpRequestsService) {}

  add(itemId: any): Promise<any> {
    return this.httpRequestService.post(WISH_ROUTE, { itemId });
  }

  async getList(): Promise<any[]> {
    const response = await this.httpRequestService.get<IAPIResponsePaged>(WISH_ROUTE);
    return response._embedded?.wishItems || [];
  }

  getOne(id: number): Promise<any> {
    return this.httpRequestService.get(WISH_ROUTE + "/" + id);
  }

  update(id: number, data: any): Promise<any> {
    return this.httpRequestService.put(WISH_ROUTE + "/" + id, data);
  }

  remove(id: number): Promise<any> {
    return this.httpRequestService.delete(WISH_ROUTE + "/" + id);
  }
}
