import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {UserTo} from "../../users/model/user-to";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  currentUserTO!: UserTo;

  constructor(public authService: AuthService) {
  }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(userTO => this.currentUserTO = userTO);
  }

  public isAuthenticated(): boolean {
    return !!this.currentUserTO;
  }
}
