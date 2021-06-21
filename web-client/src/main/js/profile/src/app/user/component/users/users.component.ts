import {Component, OnInit} from '@angular/core';
import {UserTO} from "../../../shared/model/to/user-t-o";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users!: UserTO[];
  public filterBy: string = 'ALL';
  public sortBy: string = 'name';
  public sortType: string = 'asc';

  constructor(private usersService: UserService) {
  }

  ngOnInit(): void {
    this.usersService.getUsers().subscribe(e => {
      this.users = e;
    });
  }

  public filterUsers(user: UserTO): boolean {
    if (this.filterBy == 'ALL') {
      return true;
    }
    return user.seniority === this.filterBy;
  }
}
