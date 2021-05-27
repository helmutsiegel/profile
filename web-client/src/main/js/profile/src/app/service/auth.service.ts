import {Injectable, OnDestroy} from '@angular/core';
import {UserTo} from "../users/model/user-to";
import {UsersService} from "../users/service/users.service";
import {Observable, ReplaySubject, Subject, Subscription} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnDestroy {
  private currentUserSubject!: Subject<UserTo>;
  private currentUser$!: Observable<UserTo>;
  private subscription!: Subscription;

  constructor(private usersService: UsersService) {
    this.currentUserSubject = new ReplaySubject<UserTo>(1);
    this.currentUserSubject.next(undefined);
  }

  public loginUser(username: string, password: string): void {
    this.currentUser$ = this.usersService.getUserByUsername(username);
    this.subscription = this.currentUser$.subscribe(data => {
      this.currentUserSubject.next(data)
    });
  }

  public getCurrentUser(): Observable<UserTo> {
    return this.currentUserSubject.asObservable();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  logOut() {
    this.currentUserSubject.next(undefined);
  }
}
