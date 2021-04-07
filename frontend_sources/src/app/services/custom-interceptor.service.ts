import { NotificationsService } from './notification.service';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry, finalize } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CustomInterceptorService implements HttpInterceptor {

  loading: boolean = false;

  constructor(private notificationsService: NotificationsService) { }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const authReq = req.clone({
      headers: req.headers.set('Session', '123456789'),
    });

    this.loading = true;

    return next.handle(authReq).pipe(
      catchError((err: HttpErrorResponse) => {
        this.notificationsService.openSnackBar('Request error');
        return throwError(err);
      }),
      finalize(() => {
        this.loading = false;
        const profilingMessage = `${req.method} - "${req.urlWithParams}"`;
        console.log(profilingMessage);
      })
    )
  }

}
