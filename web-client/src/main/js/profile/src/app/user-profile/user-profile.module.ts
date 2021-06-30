import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserProfileRoutingModule } from './user-profile-routing.module';
import { ProfilePageComponent } from './component/profile-page/profile-page.component';
import {ReactiveFormsModule} from "@angular/forms";
import { SettingsNavigatorComponent } from './component/settings-navigator/settings-navigator.component';
import { ChangePasswordComponent } from './component/change-password/change-password.component';


@NgModule({
  declarations: [
    ProfilePageComponent,
    SettingsNavigatorComponent,
    ChangePasswordComponent
  ],
  imports: [
    CommonModule,
    UserProfileRoutingModule,
    ReactiveFormsModule
  ]
})
export class UserProfileModule { }
