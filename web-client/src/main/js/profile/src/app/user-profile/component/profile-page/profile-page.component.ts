import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthService} from "../../../service/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  profileForm!: FormGroup;

  constructor(private authService: AuthService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(userTO => {
      let firstName = new FormControl(userTO?.firstName);
      let lastName = new FormControl(userTO?.lastName);
      this.profileForm = new FormGroup({
        lastName: lastName,
        firstName: firstName,
      })
    });
  }

  public cancel(): void {
    window.history.back();
  }

  public saveProfile(value: any) {
    this.authService.updateCurrentUser(value.firstName, value.lastName);
    this.authService.getCurrentUser().subscribe(userTO=>{
      this.router.navigate([userTO.userName, 'cv']);
    });

  }
}
