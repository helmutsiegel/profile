import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {map, tap} from "rxjs/operators";
import {AuthService} from "../../service/auth.service";
import {ToastrService} from "./toastr.service";

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  constructor(private http: HttpClient,
              private authService: AuthService,
              private toastr: ToastrService) {

  }

  public get<T>(url: string, notificationOnError?: boolean): Observable<T> {
    return this.http.get<T>(url, {observe: 'response'}).pipe(
      tap(response => this.handleToken(response, url), e => this.handleError(e, url, notificationOnError)),
      map(response => response.body as T));
  }

  public post<T>(url: string, body: any, notificationOnError?: boolean): Observable<T> {
    return this.http.post<T>(url, body, {observe: 'response'})
      .pipe(
        tap(response => this.handleToken(response, url), e => this.handleError(e, url, notificationOnError)),
        map(response => response.body as T));
  }

  public put<T>(url: string, body: any, notificationOnError?: boolean): Observable<T> {
    return this.http.put<T>(url, body, {observe: 'response'})
      .pipe(
        tap(response => this.handleToken(response, url), e => this.handleError(e, url, notificationOnError)),
        map(response => response.body as T));
  }

  private handleToken(response: HttpResponse<any>, url: string) {
    const authorization = response.headers.get('Authorization');
    if (authorization) {
      if (url === 'user/currentUser') {
        this.authService.setCurrentUser(response.body);
      }
      if (authorization !== localStorage.getItem('Authorization')) {
        localStorage.setItem('Authorization', authorization);
      }
    } else {
      localStorage.clear();
      this.authService.logOut();
    }
  }

  private handleError(error: HttpErrorResponse, url: string, notificationOnError?: boolean): void {
    if (notificationOnError) {
      if (error.status === 401) {
        this.toastr.error('You are not authorized to perform this operation!');
      }
    }
    if (url === 'user/currentUser') {
      this.authService.logOut();
    }
  }
}

