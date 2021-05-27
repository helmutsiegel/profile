import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class RestCallsInterceptor implements HttpInterceptor {

  constructor() {
  }

  /**
   * Interceptor to add rs prefix to http calls
   */
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request.clone({
      url: 'rs/' + request.url
    }));
  }
}
