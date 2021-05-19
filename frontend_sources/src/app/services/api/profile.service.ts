import { Injectable } from "@angular/core";

import { HttpRequestsService } from "../http-requests.service";
import { SessionService } from "../session.service";

import { IUserLight } from "src/app/models/iternal/IUser";
import { CHANGE_PASSWORD, CHANGE_PROFILE_DATA } from "src/environments/api.routes";

@Injectable({
  providedIn: "root",
})
export class ProfileService {
  constructor(private httpRequestsService: HttpRequestsService, private sessionService: SessionService) {}

  async changeProfileData(name: string, surname: string): Promise<any> {
    const response = await this.httpRequestsService.put<IUserLight>(CHANGE_PROFILE_DATA, { name, surname });
    this.sessionService.updateUserData(response);

    return response;
  }

  changePassword(oldPassword: string, newPassword: string, reNewPassword: string) {
    return this.httpRequestsService.post(CHANGE_PASSWORD, { oldPassword, newPassword, reNewPassword });
  }
}
