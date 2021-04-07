import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import jwtDecode from 'jwt-decode';
import { IAuthResponse } from '../models';
import { HttpRequestsService } from './http-requests.service';
import { POST_USER_LOGIN } from 'src/environments/api.routes';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends HttpRequestsService{

  userJwtData?: any = null;

  constructor(
    private router: Router,
    public http : HttpClient
  ){
    super(http);
  }

  init(){
    const token = localStorage.getItem('token');
    console.log('Init auth');

    // TODO: add token expired check

    if (token) {
      try {
        this.userJwtData = jwtDecode(token);
      } catch (error) {
        // this.userJwtData = null; // TEST
        this.userJwtData = token;
      }
    }
  }

  async logIn(login: string, password: string): Promise<string> {
    // const authObject = await this.httpService.post<IAuthResponse>(POST_USER_LOGIN, { login, password });

    // TEST
    let tokenPromise = new Promise<string>((res, rej) => {
      setTimeout(() => {
        res('token');
      }, 1200);
    });

    const token = await tokenPromise;
    localStorage.setItem('token', token);
    this.userJwtData = '';

    // localStorage.setItem('token', authObject.token);
    // this.userJwtData = jwtDecode(authObject.token);
    // return this.userJwtData;

    return token;
  }

  logOut() {
    localStorage.removeItem('token');
    this.userJwtData = null;
    this.router.navigate(['/']);
  }

  get isAuthroized(): boolean {
    return this.userJwtData != null;
  }
}
