import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable, of} from "rxjs";
import {UsersService} from "../../users/service/users.service";
import {catchError, map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UsersPageRouteActivatorService implements CanActivate {

  constructor(private userService: UsersService,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const emailFromUrl = route.params['email'];
    return this.userService.userExists(emailFromUrl).pipe(
      map(_ => {
        return true;
      }),
      catchError(_ => {
        return of(this.router.createUrlTree(['/404']))
      }));
  }
}
