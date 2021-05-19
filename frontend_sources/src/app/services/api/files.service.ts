import { Injectable } from "@angular/core";

import { HttpRequestsService } from "../http-requests.service";

import { FILES_ROUTE } from "src/environments/api.routes";

@Injectable({
  providedIn: "root",
})
export class FilesService {
  constructor(private httpRequestService: HttpRequestsService) {}

  pushFileToStorage(file: File): Promise<any> {
    const data: FormData = new FormData();
    data.append("file", file);
    return this.httpRequestService.postFile(FILES_ROUTE, data);
  }
}
