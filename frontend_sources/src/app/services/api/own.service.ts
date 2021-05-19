import { Injectable } from "@angular/core";
import { HttpRequestsService } from "../http-requests.service";

import { IAPIResponsePaged } from "src/app/models";
import { OWN_ROUTE } from "src/environments/api.routes";

@Injectable({
  providedIn: "root",
})
export class OwnService {
  // TODO: add model
  constructor(private httpRequestService: HttpRequestsService) {}

  add(itemId: any): Promise<any> {
    return this.httpRequestService.post(OWN_ROUTE, { itemId });
  }

  async getList(): Promise<any[]> {
    const response = await this.httpRequestService.get<IAPIResponsePaged>(OWN_ROUTE);
    return response._embedded?.ownItems || [];
  }

  getOne(id: number): Promise<any> {
    return this.httpRequestService.get(OWN_ROUTE + "/" + id);
  }

  update(id: number, data: any): Promise<any> {
    return this.httpRequestService.put(OWN_ROUTE + "/" + id, data);
  }

  remove(id: number): Promise<any> {
    return this.httpRequestService.delete(OWN_ROUTE + "/" + id);
  }
}
