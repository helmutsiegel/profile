import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {ToastrService} from "../../commons/service/toastr.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
  username!: string;
  password!: string;
  mouseoverLogin: boolean = false;
  private subscription!: Subscription;


  constructor(private authService: AuthService,
              private toastr: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.subscription = this.authService.getCurrentUser().subscribe(userTO => {
      if (!!userTO) {
        this.toastr.success('Welcome ' + userTO.firstName, 'Login successful!');
        this.router.navigate([userTO.userName, 'cv']);
      } else {
        this.toastr.error('Username or password incorrect!');
      }
    });
  }

  public login(formValues: any) {
    this.authService.loginUser(formValues.username, formValues.password);
  }


  public cancel(): void {
    window.history.back();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
