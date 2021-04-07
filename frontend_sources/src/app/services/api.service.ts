import { POST_USER_REGISTER } from './../../environments/api.routes';
import { IAuthResponse } from './../models/http/IAuthResponse';
import { HttpRequestsService } from './http-requests.service';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private httpService : HttpRequestsService){}

  userRegister(email: string, password: string): Promise<IAuthResponse> {
    return this.httpService.post<IAuthResponse>(POST_USER_REGISTER, { email, password });
  }
}
