import {Component, OnInit} from '@angular/core';
import {UserVo} from "../../model/user-vo";

@Component({
  selector: 'users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: UserVo[] = [{
    name: 'Max Mustermann',
    age: 25,
    jobTitle: 'Java developer',
    yearsOfExperience: 1
  }, {
    name: 'Peter Pan',
    age: 35,
    jobTitle: '.Net developer',
    yearsOfExperience: 4,
  }, {
    name: 'John Doe',
    age: 27,
    jobTitle: 'c++ developer',
    yearsOfExperience: 2,
  }]

  constructor() {
  }

  ngOnInit(): void {
  }

}