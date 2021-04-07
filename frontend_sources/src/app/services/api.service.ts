import { IAuthResponse } from './../models/http/IAuthResponse';
import { HttpRequestsService } from './http-requests.service';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private httpService : HttpRequestsService){}

  userRegister(email: string, password: string): Promise<IAuthResponse> {
    // TODO: add route
    return this.httpService.post<IAuthResponse>('', { email, password });
  }
}
