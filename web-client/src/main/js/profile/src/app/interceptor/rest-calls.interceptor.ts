import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from "rxjs/operators";

@Injectable()
export class RestCallsInterceptor implements HttpInterceptor {

  constructor() {

  }

  /**
   * Adds some extensions to the request, and updates the token if needed
   */
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(this.extendRequest(request)).pipe(tap(this.updateToken));
  }

  private updateToken(e: HttpEvent<any>): void {
    if (e instanceof HttpResponse) {
      const authorizationToken = e.headers.get('Authorization');
      if (authorizationToken && authorizationToken !== localStorage.getItem('Authorization')) {
        localStorage.setItem('Authorization', authorizationToken);
      }
    }
  }

  /**
   * Adds rs prefix to the url, and AUTHORIZATION if is present
   */
  private extendRequest(request: HttpRequest<unknown>): HttpRequest<unknown> {
    const authorization = localStorage.getItem('Authorization');
    if (authorization) {
      return request.clone({
        url: 'rs/' + request.url,
        setHeaders: {
          'Authorization': authorization
        }
      });
    } else {
      return request.clone({
        url: 'rs/' + request.url,
      });
    }
  }
}
