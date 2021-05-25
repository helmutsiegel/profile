import {Component, OnInit} from '@angular/core';
import {UserTo} from "../../model/user-to";
import {UsersService} from "../../service/users.service";
import {ToastrService} from "../../../commons/service/toastr.service";

@Component({
  selector: 'users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users!: UserTo[];

  constructor(private usersService: UsersService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.usersService.getUsers().subscribe(e => {
      this.users = e;
      this.toastr.success('Users loaded', 'Users');
    });
  }
}