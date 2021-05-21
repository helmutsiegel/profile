import {Injectable} from '@angular/core';
import {UserVo} from "../model/user-vo";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private newVar = [{
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

  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<UserVo[]> {
    return this.http.get<UserVo[]>("/rs/user");
  }
}
