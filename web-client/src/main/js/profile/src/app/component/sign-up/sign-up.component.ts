import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SignUpService} from "../../service/sign-up.service";
import {SignUpUserTO} from "../../commons/model/to/sign-up-user-t-o";

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
  private username!: FormControl;

  constructor(private signUpService: SignUpService) {
  }

  ngOnInit(): void {
    this.firstName = new FormControl();
    this.firstName.setValidators(Validators.required)
    this.lastName = new FormControl();
    this.lastName.setValidators(Validators.required)
    this.username = new FormControl();
    this.username.setValidators(Validators.required)
    this.password1 = new FormControl();
    this.password1.setValidators(Validators.required)
    this.password2 = new FormControl();
    this.password2.setValidators(Validators.required)
    this.signUpForm = new FormGroup({
      lastName: this.lastName,
      firstName: this.firstName,
      password1: this.password1,
      password2: this.password2,
      username: this.username
    })
  }

  public signUp(): void {
    this.signUpService.signUp({
      firstName: this.firstName.value,
      lastName: this.lastName.value,
      title: '',
      password1: this.password1.value,
      password2: this.password2.value,
      username: this.username.value
    } as SignUpUserTO);
  }

  public cancel(): void {
    window.history.back();
  }

  public invalidAndTouched(fieldName: string): boolean {
    const field = this.signUpForm.controls[fieldName];
    return field.invalid && field.touched
  }
}


