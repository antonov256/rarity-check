import { Injectable } from "@angular/core";

import { HttpRequestsService } from "../http-requests.service";

import { USERS_ROUTE } from "src/environments/api.routes";

@Injectable({
  providedIn: "root",
})
export class UsersService {
  constructor(private httpRequestService: HttpRequestsService) {}

  getOne(id: number) {
    return this.httpRequestService.get(USERS_ROUTE + "/" + id);
  }
}
