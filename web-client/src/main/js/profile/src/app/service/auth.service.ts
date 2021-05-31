import {Injectable, OnDestroy} from '@angular/core';
import {UserTO} from "../commons/model/to/user-t-o";
import {UsersService} from "../users/service/users.service";
import {Observable, ReplaySubject, Subject, Subscription} from "rxjs";
import {ToastrService} from "../commons/service/toastr.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnDestroy {
  private currentUserSubject!: Subject<UserTO>;
  private currentUser$!: Observable<UserTO>;
  private subscription!: Subscription;

  constructor(private usersService: UsersService,
              private toastr: ToastrService) {
    this.currentUserSubject = new ReplaySubject<UserTO>(1);
    this.currentUserSubject.next(undefined);
  }

  public loginUser(username: string, password: string): void {
    this.currentUser$ = this.usersService.getUserByUsername(username);
    this.subscription = this.currentUser$.subscribe(data => {
      this.currentUserSubject.next(data)
    });
  }

  public getCurrentUser(): Observable<UserTO> {
    return this.currentUserSubject.asObservable();
  }

  public ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public logOut() {
    this.currentUserSubject.next(undefined);
  }

  public updateCurrentUser(firstName: string, lastName: string) {
    this.toastr.warning('Dont forget to implement update on BE side');
  }
}
