import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'users-navbar',
  templateUrl: './users-navbar.component.html',
  styleUrls: ['./users-navbar.component.css']
})
export class UsersNavbarComponent implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
  }

  public getCSSClassForSidebar(link: string) {
    return this.router.url == '/' + this.route.snapshot.params['username'] + '/' + link ? 'btn-dark' : 'btn-light btn-outline-secondary';
  }

  navigate(link: string) {
    this.router.navigate([this.route.snapshot.params['username'], link]);
  }
}
