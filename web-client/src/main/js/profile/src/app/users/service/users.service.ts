import {Injectable} from '@angular/core';
import {UserVo} from "../model/user-vo";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor() {
  }

  public getUsers(): UserVo[] {
    return [{
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
    }];
  }
}
