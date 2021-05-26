import {Injectable} from '@angular/core';
import {UserTo} from "../users/model/user-to";
import {UsersService} from "../users/service/users.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUser!: UserTo;

  constructor(private usersService: UsersService) {
  }

  public loginUser(username: string, password: string): void {
    this.usersService.getUserByUsername(username).subscribe(
      userTO => this.currentUser = userTO);
  }

  public isAuthenticated(): boolean {
    return !!this.currentUser;
  }

  public getCurrentUsersName(): string {
    return this.currentUser?.firstName + ' ' + this.currentUser.lastName
  }

  public getCurrentUsersUsername(): string {
    return this.currentUser?.userName
  }
}
