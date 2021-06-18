import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanDeactivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {map} from "rxjs/operators";
import {AuthService} from "../../service/auth.service";

@Injectable({
  providedIn: 'root'
})
export class UserNotLoggedInGuard implements CanActivate {
  constructor(private authService: AuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.authService.getCurrentUser().pipe(
      map(userTO => {
        return !userTO;
      }));
  }
}
