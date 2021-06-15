import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {ToastrService} from "../../commons/service/toastr.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
import {UsersService} from "../../users/service/users.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username!: string;
  password!: string;
  mouseoverLogin: boolean = false;

  constructor(private authService: AuthService,
              private userService: UsersService,
              private toastr: ToastrService,
              public router: Router) {
  }

  ngOnInit(): void {

  }

  public login(formValues: any) {
    this.userService.login(formValues.username, formValues.password)
      .subscribe(userTO => {
        if (userTO) {
          this.authService.setCurrentUser(userTO);
          this.toastr.success('Welcome ' + userTO.firstName, 'Login successful!');
          this.router.navigate([userTO.username, 'cv']);
        } else {
          this.toastr.error('Username or password incorrect!');
        }
      });
  }


  public cancel(): void {
    window.history.back();
  }
}
