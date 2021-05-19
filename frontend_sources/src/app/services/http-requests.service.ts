import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root",
})
export class HttpRequestsService {
  constructor(public http: HttpClient) {}

  get<T>(url: string, headersObj: any = {}): Promise<T> {
    return this.request<T>(url, "GET", headersObj);
  }

  post<T>(url: string, data: any, headers?: any): Promise<T> {
    return this.request<T>(url, "POST", headers, data);
  }

  postFile<T>(url: string, data: any): Promise<T> {
    return this.request<T>(url, "POST", null, data, "no headers");
  }

  put<T>(url: string, data: any, headers?: any): Promise<T> {
    return this.request<T>(url, "PUT", headers, data);
  }

  delete<T>(url: string, data: any = {}): Promise<T> {
    return this.request<T>(url, "DELETE", null, data);
  }

  // TODO: fix request type
  private async request<T>(
    url: string,
    method: "DELETE" | "PUT" | "GET" | "POST" = "GET",
    headersObj: any = null,
    data: any = null,
    type: "default" | "no headers" = "default"
  ): Promise<T> {
    let body;

    let headers = {};

    if (type === "default") {
      headers = {
        "Content-Type": "application/json",
        ...headersObj,
      };
    }

    if (data) {
      body = data;
    }

    try {
      const response = await this.http
        .request<T>(method, environment.serverUrl + url, {
          headers,
          body,
        })
        .toPromise();
      return response;
    } catch (e) {
      throw e;
    }
  }
}
