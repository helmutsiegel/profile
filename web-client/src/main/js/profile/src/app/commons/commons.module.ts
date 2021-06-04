import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersNavbarComponent} from "./component/users-navbar/users-navbar.component";
import {RouterModule} from "@angular/router";
import { Error404Component } from './component/error404/error404.component';
import { CollapsibleCardComponent } from './component/collapsible-card/collapsible-card.component';
import { SmilingPipe } from './pipe/smiling.pipe';
import {PersonalInfoComponent} from "./component/personal-info/personal-info.component";


@NgModule({
    declarations: [
        UsersNavbarComponent,
        Error404Component,
        CollapsibleCardComponent,
        SmilingPipe,
        PersonalInfoComponent
    ],
  imports: [
    CommonModule,
    RouterModule
  ],
    exports: [
        UsersNavbarComponent,
        CollapsibleCardComponent,
        SmilingPipe,
        PersonalInfoComponent
    ]
})
export class CommonsModule {
}
