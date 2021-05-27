import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

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
              private router: Router) {
  }

  ngOnInit(): void {
  }

  public login(formValues: any) {
    this.authService.loginUser(formValues.username, formValues.password);
    this.router.navigate([this.authService.getCurrentUsersUsername(), 'cv']);
  }

  public cancel(): void {
    this.router.navigate([this.authService.getCurrentUsersUsername(), 'cv']);
  }
}
