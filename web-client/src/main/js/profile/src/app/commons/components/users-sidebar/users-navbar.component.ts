import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'users-navbar',
  templateUrl: './users-navbar.component.html',
  styleUrls: ['./users-navbar.component.css']
})
export class UsersNavbarComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public getClassForSidebar(link: string) {
    return this.router.url==link ? 'btn-dark' : 'btn-light btn-outline-secondary';
  }

}
