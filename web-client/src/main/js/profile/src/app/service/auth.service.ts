import {Injectable} from '@angular/core';
import {UserTO} from "../shared/model/to/user-t-o";
import {Observable, ReplaySubject, Subject} from "rxjs";
import {tap} from "rxjs/operators";
import {ActivatedRoute} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject!: Subject<UserTO>;
  private currentUser!: UserTO

  constructor() {
    this.currentUserSubject = new ReplaySubject<UserTO>(1);
  }

  public getCurrentUser(): Observable<UserTO> {
    return this.currentUserSubject.asObservable()
      .pipe(tap(userTO => {
        this.currentUser = userTO;
      }));
  }

  public logOut() {
    localStorage.clear();
    this.currentUserSubject.next(undefined);
  }

  public setCurrentUser(userTO: UserTO): void {
    this.currentUserSubject.next(userTO);
  }

  public loggedInUserIsOnTheyPage(activatedRoute: ActivatedRoute): boolean {
    return !!this.currentUser && this.currentUser.email === activatedRoute.snapshot.params['email']
  }
}
