import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UsersNavbarComponent} from "./component/users-navbar/users-navbar.component";
import {RouterModule} from "@angular/router";
import { Error404Component } from './component/error404/error404.component';
import { CollapsibleCardComponent } from './component/collapsible-card/collapsible-card.component';
import { SmilingPipe } from './pipe/smiling.pipe';
import {PersonalInfoComponent} from "./component/personal-info/personal-info.component";
import { SimpleModalComponent } from './component/simple-modal/simple-modal.component';
import { SimpleTextCardComponent } from './component/simple-text-card/simple-text-card.component';
import {FormsModule} from "@angular/forms";
import { EditPencilComponent } from './component/edit-pencil/edit-pencil.component';


@NgModule({
    declarations: [
        UsersNavbarComponent,
        Error404Component,
        CollapsibleCardComponent,
        SmilingPipe,
        PersonalInfoComponent,
        SimpleModalComponent,
        SimpleTextCardComponent,
        EditPencilComponent
    ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
    exports: [
        UsersNavbarComponent,
        CollapsibleCardComponent,
        SmilingPipe,
        PersonalInfoComponent,
        SimpleModalComponent,
        SimpleTextCardComponent
    ]
})
export class CommonsModule {
}
