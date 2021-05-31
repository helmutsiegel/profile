import {Injectable} from '@angular/core';
import {UserTO} from "../../commons/model/to/user-t-o";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<UserTO[]> {
    return this.http.get<UserTO[]>('user');
  }

  public userExists(username: string): Observable<any> {
    return this.http.get('user/exists/' + username);
  }

  public getUserByUsername(username: string): Observable<any> {
    return this.http.get('user/' + username);
  }
}
