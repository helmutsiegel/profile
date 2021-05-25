import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersNavbarComponent} from "./component/users-navbar/users-navbar.component";
import {RouterModule} from "@angular/router";
import { Error404Component } from './component/error404/error404.component';


@NgModule({
  declarations: [
    UsersNavbarComponent,
    Error404Component
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
