import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {UserTO} from "../../commons/model/to/user-t-o";
import {UsersService} from "../../users/service/users.service";
import {ToastrService} from "../../commons/service/toastr.service";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  currentUserTO!: UserTO;
  searchTerm: string = '';
  foundUsers: UserTO[] = []

  constructor(public authService: AuthService,
              private userService: UsersService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(userTO => this.currentUserTO = userTO);
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
}
