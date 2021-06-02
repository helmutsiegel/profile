import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersNavbarComponent} from "./component/users-navbar/users-navbar.component";
import {RouterModule} from "@angular/router";
import { Error404Component } from './component/error404/error404.component';
import { CollapsibleCardComponent } from './component/collapsible-card/collapsible-card.component';


@NgModule({
  declarations: [
    UsersNavbarComponent,
    Error404Component,
    CollapsibleCardComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
    exports: [
        UsersNavbarComponent,
        CollapsibleCardComponent
    ]
})
export class CommonsModule {
}
