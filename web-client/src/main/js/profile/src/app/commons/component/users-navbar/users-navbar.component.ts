import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AppStateService} from "../../service/app-state.service";

@Component({
  selector: 'users-navbar',
  templateUrl: './users-navbar.component.html',
  styleUrls: ['./users-navbar.component.css']
})
export class UsersNavbarComponent implements OnInit {

  constructor(private router: Router,
              private appStateService: AppStateService) {
  }

  ngOnInit(): void {
  }

  public getCSSClassForSidebar(link: string) {
    return this.router.url == this.getRouterLink(link) ? 'btn-dark' : 'btn-light btn-outline-secondary';
  }

  getRouterLink(link: string) {
    return '/' + this.appStateService.getSelectedUsername() + link;
  }
}
