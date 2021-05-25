import {Component, Input, OnInit} from '@angular/core';
import {UserTo} from "../../model/user-to";
import {Router} from "@angular/router";

@Component({
  selector: 'user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {

  @Input() userTo!: UserTo;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public openUsersPage(): void {
    this.router.navigate([this.userTo.userName, 'cv']);
  }
}
