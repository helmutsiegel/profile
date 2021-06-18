import {Injectable, OnDestroy} from '@angular/core';
import {UserTO} from "../shared/model/to/user-t-o";
import {Observable, ReplaySubject, Subject, Subscription} from "rxjs";
import {ToastrService} from "../shared/service/toastr.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnDestroy {
  private currentUserSubject!: Subject<UserTO>;
  private subscription!: Subscription;

  constructor(private toastr: ToastrService) {
    this.currentUserSubject = new ReplaySubject<UserTO>(1);
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

  public setCurrentUser(userTO: UserTO): void {
    this.currentUserSubject.next(userTO);
  }
}
