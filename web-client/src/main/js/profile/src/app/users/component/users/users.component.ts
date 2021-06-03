import {Component, OnInit} from '@angular/core';
import {UserTO} from "../../../commons/model/to/user-t-o";
import {UsersService} from "../../service/users.service";

@Component({
  selector: 'users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users!: UserTO[];
  public filterBy: string = 'ALL';
  public sortBy: string = 'name';

  constructor(private usersService: UsersService) {
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
    console.log(user.seniority)
    return user.seniority === this.filterBy;
  }
}
