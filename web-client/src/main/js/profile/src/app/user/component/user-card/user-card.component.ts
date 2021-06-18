import {Component, Input, OnInit} from '@angular/core';
import {UserTO} from "../../../shared/model/to/user-t-o";
import {Router} from "@angular/router";

@Component({
  selector: 'user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {

  @Input() userTO!: UserTO;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public openUsersPage(): void {
    this.router.navigate([this.userTO.email, 'cv']);
  }
}
