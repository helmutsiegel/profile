import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {UserTO} from "../../shared/model/to/user-t-o";
import {UsersService} from "../../users/service/users.service";
import {ToastrService} from "../../shared/service/toastr.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {
  currentUserTO!: UserTO;
  searchTerm: string = '';
  foundUsers: UserTO[] = []
  private subscription!: Subscription;

  constructor(public authService: AuthService,
              private userService: UsersService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.subscription = this.authService.getCurrentUser().subscribe(userTO => {
      this.currentUserTO = userTO
    });
  }

  public isAuthenticated(): boolean {
    return !!this.currentUserTO;
  }

  public searchUsers(searchTerm: string) {
    this.userService.searchUsers(searchTerm).subscribe(
      users => {
        this.foundUsers = users;
      }
    )
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
