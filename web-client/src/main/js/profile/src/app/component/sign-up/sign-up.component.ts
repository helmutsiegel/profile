import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SignUpService} from "../../service/sign-up.service";
import {SignUpUserTO} from "../../shared/model/to/sign-up-user-t-o";
import {ToastrService} from "../../shared/service/toastr.service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {UserService} from "../../user/service/user.service";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  signUpForm!: FormGroup;

  private firstName!: FormControl;
  private lastName!: FormControl;
  private password1!: FormControl;
  private password2!: FormControl;
  private email!: FormControl;

  constructor(private signUpService: SignUpService,
              private toastr: ToastrService,
              private userService: UserService,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.firstName = new FormControl();
    this.firstName.setValidators(Validators.required)
    this.lastName = new FormControl();
    this.lastName.setValidators(Validators.required)
    this.email = new FormControl();
    this.email.setValidators(Validators.required)
    this.password1 = new FormControl();
    this.password1.setValidators(Validators.required)
    this.password2 = new FormControl();
    this.password2.setValidators(Validators.required)
    this.signUpForm = new FormGroup({
      lastName: this.lastName,
      firstName: this.firstName,
      password1: this.password1,
      password2: this.password2,
      email: this.email
    })
  }

  public signUp(): void {
    this.signUpService.signUp({
      firstName: this.firstName.value,
      lastName: this.lastName.value,
      title: '',
      password1: this.password1.value,
      password2: this.password2.value,
      email: this.email.value
    } as SignUpUserTO).subscribe(
      userTO => {
        this.toastr.success('Successful registration');
        this.userService.login(userTO.email, userTO.password1).subscribe(userTO =>{
          this.router.navigate([userTO.email, 'cv']);
        });
      },
      error => {
        this.toastr.error('Registration failed.');
      }
    );
  }

  public cancel(): void {
    window.history.back();
  }

  public invalidAndTouched(fieldName: string): boolean {
    const field = this.signUpForm.controls[fieldName];
    return field.invalid && field.touched
  }
}


