import {Injectable} from '@angular/core';
import {UserTO} from "../../shared/model/to/user-t-o";
import {Observable} from "rxjs";
import {BackendService} from "../../shared/service/backend.service";
import {ChangePasswordTO} from "../../shared/model/to/change-password-t-o";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private backend: BackendService) {
  }

  public getUsers(): Observable<UserTO[]> {
    return this.backend.get<UserTO[]>('user');
  }

  public searchUsers(searchTerm: string): Observable<UserTO[]> {
    return this.backend.get<UserTO[]>('user/search/' + searchTerm);
  }

  public userExists(email: string): Observable<any> {
    return this.backend.get('user/exists/' + email);
  }

  public getCurrentUser(): Observable<any> {
    return this.backend.get<UserTO>('user/currentUser');
  }

  public login(email: string, password: string): Observable<UserTO> {
    return this.backend.post<UserTO>('user/login', {email: email, password: password});
  }

  public updateCurrentUser(userTO: UserTO): Observable<UserTO> {
    return this.backend.put<UserTO>('user', userTO);
  }

  public changePassword(changePasswordTO: ChangePasswordTO): Observable<any> {
    return this.backend.post('user/changePassword', changePasswordTO);
  }
}
