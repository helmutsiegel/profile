import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersSidebarComponent} from "./components/users-sidebar/users-sidebar.component";


@NgModule({
  declarations: [
    UsersSidebarComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    UsersSidebarComponent
  ]
})
export class CommonsModule {
}
