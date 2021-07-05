import {Injectable, OnDestroy} from '@angular/core';
import {UserTO} from "../shared/model/to/user-t-o";
import {Observable, ReplaySubject, Subject, Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnDestroy {
  private currentUserSubject!: Subject<UserTO>;
  private currentUser!: UserTO
  private subscription!: Subscription;

  constructor() {
    this.currentUserSubject = new ReplaySubject<UserTO>(1);
    this.subscription = this.getCurrentUser().subscribe(userTO => {
      this.currentUser = userTO;
    });
  }

  public getCurrentUser(): Observable<UserTO> {
    return this.currentUserSubject.asObservable();
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

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
