import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IAPIResponse } from '../models';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestsService {

  constructor(public http: HttpClient) {}

  get<T>(url: string, headersObj: any): Promise<T> {
    return this.request(url, 'GET', headersObj);
  }

  post<T>(url: string, data: any): Promise<T> {
    return this.request<T>(url, 'POST', null, data);
  }

  patch<T>(url: string, data: any): Promise<T> {
    return this.request(url, 'PATCH', null, data);
  }

  delete<T>(url: string, data: any): Promise<T> {
    return this.request(url, 'DELETE', null, data);
  }


  // TODO: fix request type
  private async request<T>(
    url: string,
    method: 'DELETE' | 'PATCH' | 'GET' | 'POST' = 'GET',
    headersObj: any = null,
    data: any = null
  ): Promise<T> {
    let body;

    const headers = {
      authorization: localStorage.getItem('token') || '',
      ...headersObj,
    };
    if (data) {
      headers['Content-Type'] = 'application/json';
      body = data;
    }
    try {
      const response = await this.http
        .request<IAPIResponse<T>>(method, environment.serverUrl + url, {
          headers,
          body,
        })
        .toPromise();
      return response.response;
    } catch (e) {
      throw e;
    }
  }
}
