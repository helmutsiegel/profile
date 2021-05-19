import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'users-sidebar',
  templateUrl: './users-sidebar.component.html',
  styleUrls: ['./users-sidebar.component.css']
})
export class UsersSidebarComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public getClassForSidebar(link: string) {
    return this.router.url==link ? 'btn btn-dark' : 'btn btn-light btn-outline-secondary';
  }

}
