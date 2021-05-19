import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-users-sidebar',
  templateUrl: './users-sidebar.component.html',
  styleUrls: ['./users-sidebar.component.css']
})
export class UsersSidebarComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  navigate(link: string): void {
    this.router.navigate([link], {});
  }

  getClassForSidebar(link: string) {
    return this.router.url==link ? 'btn btn-primary' : 'btn btn-secondary';
  }

}
