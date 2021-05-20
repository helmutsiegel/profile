import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersNavbarComponent} from "./components/users-navbar/users-navbar.component";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    UsersNavbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    UsersNavbarComponent
  ]
})
export class CommonsModule {
}
