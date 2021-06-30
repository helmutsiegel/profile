import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../service/auth.service";
import {UserService} from "../../../user/service/user.service";
import {UserTO} from "../../../shared/model/to/user-t-o";
import {ToastrService} from "../../../shared/service/toastr.service";
import {ChangePasswordTO} from "../../../shared/model/to/change-password-t-o";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  profileForm!: FormGroup;
  private firstName!: FormControl;
  private lastName!: FormControl;
  private title!: FormControl;
  private currentUser!: UserTO;

  constructor(private authService: AuthService,
              private userService: UserService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(userTO => {
      this.currentUser = {...userTO};
      this.firstName = new FormControl(userTO?.firstName, Validators.required);
      this.lastName = new FormControl(userTO?.lastName, Validators.required);
      this.title = new FormControl(userTO?.title, Validators.required);
      this.profileForm = new FormGroup({
        lastName: this.lastName,
        firstName: this.firstName,
        title: this.title
      })
    });
  }

  public cancel(): void {
    window.history.back();
  }

  public saveProfile(formValues: any, closeAfterSave?: boolean) {
    if (this.profileForm.valid) {
      this.currentUser.firstName = formValues.firstName;
      this.currentUser.lastName = formValues.lastName;
      this.currentUser.title = formValues.title;
      this.userService.updateCurrentUser(this.currentUser).subscribe(userTO => {
        if (closeAfterSave) {
          this.cancel();
        }
        this.toastr.success('Save successful!')
        this.authService.setCurrentUser(userTO);
      });
    }
  }

  public firstNameInValid(): boolean {
    return this.firstName.invalid && this.profileForm.controls.firstName.touched
  }

  public lastNameInValid(): boolean {
    return this.lastName.invalid && this.profileForm.controls.lastName.touched
  }

  public titleInValid(): boolean {
    return this.lastName.invalid && this.profileForm.controls.lastName.touched
  }

  public changePassword(changePasswordTO: ChangePasswordTO): void {
    this.userService.changePassword(changePasswordTO).subscribe(
      _ => {
        this.toastr.success('Password changed successfully')
      },
      err => {
        this.toastr.error(err.error);
      }
    )
    ;
  }
}
