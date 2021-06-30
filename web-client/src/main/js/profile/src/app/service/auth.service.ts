import {Injectable} from '@angular/core';
import {UserTO} from "../shared/model/to/user-t-o";
import {Observable, ReplaySubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject!: Subject<UserTO>;


  constructor() {
    this.currentUserSubject = new ReplaySubject<UserTO>(1);
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
}
