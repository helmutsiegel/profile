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

  public searchUsers(searchTerm: string): Observable<UserTO[]> {
    return this.http.get<UserTO[]>('user/search/' + searchTerm);
  }

  public userExists(username: string): Observable<any> {
    return this.http.get('user/exists/' + username);
  }

  public getUserByUsername(username: string): Observable<any> {
    return this.http.get<UserTO>('user/' + username);
  }

  public login(username: string, password: string): Observable<any> {
    return this.http.post('user/login', {username: username, password: password}, {observe: 'response'});
  }
}
