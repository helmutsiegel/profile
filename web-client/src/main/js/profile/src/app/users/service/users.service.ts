import {Injectable} from '@angular/core';
import {UserTo} from "../model/user-to";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<UserTo[]> {
    return this.http.get<UserTo[]>('rs/user');
  }

  public userExists(username: string): Observable<UserTo> {
    return this.http.get<UserTo>('rs/user/' + username);
  }
}
