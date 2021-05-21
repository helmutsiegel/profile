import {Component, OnInit} from '@angular/core';
import {UserVo} from "../../model/user-vo";
import {UsersService} from "../../service/users.service";
import {ToastrService} from "../../../commons/service/toastr.service";

@Component({
  selector: 'users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users!: UserVo[];

  constructor(private usersService: UsersService,
              private toastr: ToastrService) {

  }

  ngOnInit(): void {
    this.users = this.usersService.getUsers();
    this.toastr.success("Users loaded", "Users");
  }
}
