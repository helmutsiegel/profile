import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username!: string;
  password!: string;

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  public login(formValues: any) {
    this.authService.loginUser(formValues.username, formValues.password)
  }
}
