import { Injectable } from "@angular/core";

import { HttpRequestsService } from "../http-requests.service";
import { SessionService } from "../session.service";

import { IAPIResponsePaged, ISubcategory } from "src/app/models";
import { SUBCATEGORIES_UPDATE_INTERVAL } from "src/environments/api.pops";
import { SUBCATEGORIES_ROUTE } from "src/environments/api.routes";

@Injectable({
  providedIn: "root",
})
export class SubcategoriesService {
  lastUpdate = 0;
  storage: ISubcategory[] = [];

  constructor(private httpRequestService: HttpRequestsService, private sessionService: SessionService) {}

  create(data: ISubcategory): Promise<ISubcategory> {
    return this.httpRequestService.post(SUBCATEGORIES_ROUTE, data);
  }

  async getAll(): Promise<ISubcategory[]> {
    const currentTime = Date.now();

    if (this.lastUpdate + SUBCATEGORIES_UPDATE_INTERVAL < currentTime || this.sessionService.isAdmin) {
      const response = await this.httpRequestService.get<IAPIResponsePaged>(SUBCATEGORIES_ROUTE);

      this.lastUpdate = currentTime;
      this.storage = response._embedded.subcategory;
    }

    return this.storage;
  }

  getOne(id: number): Promise<ISubcategory> {
    return this.httpRequestService.get(SUBCATEGORIES_ROUTE + "/" + id);
  }

  update(id: number, data: ISubcategory): Promise<ISubcategory> {
    return this.httpRequestService.put(SUBCATEGORIES_ROUTE + "/" + id, data);
  }

  delete(id: number): Promise<ISubcategory> {
    return this.httpRequestService.delete(SUBCATEGORIES_ROUTE + "/" + id);
  }
}
