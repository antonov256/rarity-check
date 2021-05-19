import { Injectable } from "@angular/core";

import { HttpRequestsService } from "../http-requests.service";

import { LOGIN_ROUTE, REGISTER_ROUTE, LOGOUT_ROUTE, REFRESH_ROUTE } from "src/environments/api.routes";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  constructor(private httpRequestService: HttpRequestsService) {}

  register(email: string, fullname: string, password: string, rePassword: string, username: string): Promise<any> {
    const fullnameArray = fullname.split(" ");
    const timezoneOffset = new Date().getTimezoneOffset() / 60;

    const name = fullnameArray[0];
    const surname = fullnameArray[1] || "";
    const timezone = timezoneOffset < 0 ? "GMT+" + Math.abs(timezoneOffset) : "GMT-" + timezoneOffset;

    return this.httpRequestService.post(REGISTER_ROUTE, {
      email,
      username,
      password,
      rePassword,
      name,
      surname,
      timezone,
    });
  }

  login(username: string, password: string): Promise<any> {
    return this.httpRequestService.post(LOGIN_ROUTE, { username, password });
  }

  refresh(): Promise<any> {
    return this.httpRequestService.post(REFRESH_ROUTE, {});
  }

  logout(): Promise<any> {
    return this.httpRequestService.post(LOGOUT_ROUTE, {});
  }
}
