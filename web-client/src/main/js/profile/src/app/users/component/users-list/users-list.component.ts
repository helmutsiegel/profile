import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {UserTO} from "../../../shared/model/to/user-t-o";

@Component({
  selector: 'users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit, OnChanges {

  @Input()
  public users!: UserTO[];

  public visibleUsers: UserTO[] = [];

  @Input()
  public filterBy!: string;

  @Input()
  public sortBy!: string;

  @Input()
  public sortType!: string;

  constructor() {
  }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    if (this.users) {
      this.filterUsers();
    }
  }

  private filterUsers() {
    const filteredUsers = this.filterBy !== 'ALL' ? this.users.filter(userTO => userTO.seniority === this.filterBy) : this.users.slice(0);
    const sorted = this.sortBy === 'name' ? filteredUsers.sort(this.sortByName,) : filteredUsers.sort(this.sortBySeniority);
    this.visibleUsers = this.sortType === 'asc' ? sorted : sorted.reverse();
  }

  private sortByName(a: UserTO, b: UserTO): number {
    return (a.firstName + a.lastName).localeCompare(b.firstName + b.lastName);
  }

  private sortBySeniority(a: UserTO, b: UserTO): number {
    return a.seniority.localeCompare(b.seniority)
  }
}
