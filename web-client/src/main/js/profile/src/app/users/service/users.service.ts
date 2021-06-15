import {Injectable} from '@angular/core';
import {UserTO} from "../../commons/model/to/user-t-o";
import {Observable} from "rxjs";
import {BackendService} from "../../commons/service/backend.service";
import {AuthService} from "../../service/auth.service";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private backend: BackendService,
              private authService: AuthService) {
  }

  public getUsers(): Observable<UserTO[]> {
    return this.backend.get<UserTO[]>('user');
  }

  public searchUsers(searchTerm: string): Observable<UserTO[]> {
    return this.backend.get<UserTO[]>('user/search/' + searchTerm);
  }

  public userExists(username: string): Observable<any> {
    return this.backend.get('user/exists/' + username);
  }

  public getCurrentUser(): Observable<any> {
    return this.backend.get<UserTO>('user/currentUser');
  }

  public login(username: string, password: string): Observable<UserTO> {
    return this.backend.post<UserTO>('user/login', {username: username, password: password})
  }
}
