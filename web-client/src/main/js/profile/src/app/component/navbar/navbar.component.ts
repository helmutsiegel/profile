import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {UserTO} from "../../commons/model/to/user-t-o";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  currentUserTO!: UserTO;

  constructor(public authService: AuthService) {
  }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(userTO => this.currentUserTO = userTO);
  }

  public isAuthenticated(): boolean {
    return !!this.currentUserTO;
  }
}
