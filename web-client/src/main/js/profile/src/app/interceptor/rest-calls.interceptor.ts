import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class RestCallsInterceptor implements HttpInterceptor {

  constructor() {
  }

  /**
   * Interceptor to add rs prefix and authorization token to http calls
   */
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const authorization = localStorage.getItem('Authorization');
    if (authorization) {
      return next.handle(request.clone({
        url: 'rs/' + request.url,
        setHeaders: {
          'Authorization': authorization
        }
      }));
    } else {
      return next.handle(request.clone({
        url: 'rs/' + request.url,
      }));
    }
  }
}
