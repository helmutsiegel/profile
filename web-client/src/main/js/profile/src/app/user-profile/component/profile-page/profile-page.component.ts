import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  profileForm!: FormGroup;
  private firstName!: FormControl;
  private lastName!: FormControl;

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(userTO => {
      this.firstName = new FormControl(userTO?.firstName, Validators.required);
      this.lastName = new FormControl(userTO?.lastName, Validators.required);
      this.profileForm = new FormGroup({
        lastName: this.lastName,
        firstName: this.firstName,
      })
    });
  }

  public cancel(): void {
    window.history.back();
  }

  public saveProfile(value: any) {
    if (this.profileForm.valid) {
      this.authService.updateCurrentUser(value.firstName, value.lastName);
      this.authService.getCurrentUser().subscribe(userTO => {
        this.router.navigate([userTO.email, 'cv']);
      });
    }
  }

  public firstNameInValid(): boolean {
    return this.firstName.invalid && this.profileForm.controls.firstName.touched
  }

  public lastNameInValid(): boolean {
    return this.lastName.invalid && this.profileForm.controls.lastName.touched
  }
}
