import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {ToastrService} from "../../shared/service/toastr.service";
import {Router} from "@angular/router";
import {UserService} from "../../user/service/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email!: string;
  password!: string;
  mouseoverLogin: boolean = false;

  constructor(private authService: AuthService,
              private userService: UserService,
              private toastr: ToastrService,
              public router: Router) {
  }

  ngOnInit(): void {

  }

  public login(formValues: any) {
    this.userService.login(formValues.email, formValues.password)
      .subscribe(userTO => {
          this.toastr.success('Welcome ' + userTO.firstName, 'Login successful!');
          this.router.navigate([userTO.email, 'cv']);
        },
        _ => {
          this.toastr.error('Email or password incorrect!');
        });
  }

  public cancel(): void {
    window.history.back();
  }
}
