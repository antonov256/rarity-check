import { LoaderService } from "./loader.service";
import { NotificationsService } from "./notification.service";
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { catchError, finalize } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class CustomInterceptorService implements HttpInterceptor {
  constructor(private notificationsService: NotificationsService, private loaderService: LoaderService) {}

  activeRequests = 0;

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.activeRequests === 0) {
      this.loaderService.startLoading();
    }
    this.activeRequests++;

    const authReq = req.clone({
      withCredentials: true,
    });

    return next.handle(authReq).pipe(
      catchError((err: HttpErrorResponse) => {
        this.notificationsService.openSnackBar(err.error.message || "Request error");
        return throwError(err);
      }),
      finalize(() => {
        this.activeRequests--;
        if (this.activeRequests === 0) {
          this.loaderService.stopLoading();
        }
        const profilingMessage = `${req.method} - "${req.urlWithParams}"`;
        console.log(profilingMessage);
      })
    );
  }
}
