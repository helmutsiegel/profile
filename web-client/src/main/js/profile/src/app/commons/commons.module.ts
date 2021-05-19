import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersSidebarComponent} from "./components/users-sidebar/users-sidebar.component";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    UsersSidebarComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    UsersSidebarComponent
  ]
})
export class CommonsModule {
}
