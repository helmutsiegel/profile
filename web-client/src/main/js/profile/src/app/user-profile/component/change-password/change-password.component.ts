import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ChangePasswordTO} from "../../../shared/model/to/change-password-t-o";

@Component({
  selector: 'change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  changePasswordForm!: FormGroup;
  currentPassword!: FormControl;
  newPassword1!: FormControl;
  newPassword2!: FormControl;

  @Output() onSave: EventEmitter<ChangePasswordTO> = new EventEmitter<ChangePasswordTO>()
  messageForNewPassword1: string = 'Required';
  messageForNewPassword2: string = 'Required';

  constructor() {
  }

  ngOnInit(): void {
    this.currentPassword = new FormControl('', Validators.required);
    this.newPassword1 = new FormControl('', Validators.required);
    this.newPassword2 = new FormControl('', Validators.required);
    this.changePasswordForm = new FormGroup({
      currentPassword: this.currentPassword,
      newPassword1: this.newPassword1,
      newPassword2: this.newPassword2
    });
  }

  public saveProfile(formValues: any) {
    if (this.changePasswordForm.valid) {
      if (formValues.newPassword1 !== formValues.newPassword2) {
        this.messageForNewPassword1 = formValues.newPassword1 === '' ? 'Required' : 'Passwords does not match';
        this.messageForNewPassword2 = formValues.newPassword2 === '' ? 'Required' : 'Passwords does not match';
        this.newPassword1.setErrors({'incorrect': true});
        this.newPassword2.setErrors({'incorrect': true});
      } else {
        this.onSave.emit({
          currentPassword: formValues.currentPassword,
          newPassword1: formValues.newPassword1,
          newPassword2: formValues.newPassword2
        });
      }
    }
  }

  public newPasswordEdited(): void {
    if (this.newPassword1.touched && this.newPassword2.touched) {
      if (this.newPassword1.value === this.newPassword2.value && this.newPassword1.value !== '') {
        this.newPassword1.setErrors(null);
        this.newPassword2.setErrors(null);
      } else {
        if (this.newPassword1.value === '' && this.newPassword2.value === '') {
          this.messageForNewPassword1 = 'Required';
          this.messageForNewPassword2 = 'Required';
          this.newPassword1.setErrors({'incorrect': true});
          this.newPassword2.setErrors({'incorrect': true});
        } else if (this.newPassword1.value === '') {
          this.messageForNewPassword1 = 'Required';
          this.newPassword1.setErrors({'incorrect': true});
          this.newPassword2.setErrors(null);
        } else if (this.newPassword2.value === '') {
          this.messageForNewPassword2 = 'Required';
          this.newPassword1.setErrors(null);
          this.newPassword2.setErrors({'incorrect': true});
        } else {
          this.messageForNewPassword1 = 'Passwords does not match';
          this.messageForNewPassword2 = 'Passwords does not match';
          this.newPassword1.setErrors({'incorrect': true});
          this.newPassword2.setErrors({'incorrect': true});
        }
      }
    }
  }
}
