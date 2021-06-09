import {Injectable, OnDestroy} from '@angular/core';
import {UserTO} from "../commons/model/to/user-t-o";
import {UsersService} from "../users/service/users.service";
import {Observable, ReplaySubject, Subject, Subscription} from "rxjs";
import {ToastrService} from "../commons/service/toastr.service";
import {HttpResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnDestroy {
  private currentUserSubject!: Subject<UserTO>;
  private subscription!: Subscription;

  constructor(private usersService: UsersService,
              private toastr: ToastrService) {
    this.currentUserSubject = new ReplaySubject<UserTO>(1);
    if (localStorage.getItem('Authorization')) {
      usersService.getUserCurrentUser().subscribe(userTO => {
          this.currentUserSubject.next(userTO);
        },
        _ => this.currentUserSubject.next(undefined));
    } else {
      this.currentUserSubject.next(undefined);
    }
  }

  public loginUser(username: string, password: string): void {
    this.usersService.login(username, password)
      .subscribe((httpResp: HttpResponse<any>) => {
        const authorization = httpResp.headers.get('Authorization');
        if (authorization) {
          localStorage.setItem('Authorization', authorization);
          this.currentUserSubject.next(httpResp.body);
        }
      });
  }

  public getCurrentUser(): Observable<UserTO> {
    return this.currentUserSubject.asObservable();
  }

  public ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public logOut() {
    localStorage.clear();
    this.currentUserSubject.next(undefined);
  }

  public updateCurrentUser(firstName: string, lastName: string) {
    this.toastr.warning('Dont forget to implement update on BE side');
  }
}
