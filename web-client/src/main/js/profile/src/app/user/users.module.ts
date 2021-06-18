import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UsersRoutingModule} from './users-routing.module';
import {UsersComponent} from './component/users/users.component';
import {UserCardComponent} from './component/user-card/user-card.component';
import { UsersListComponent } from './component/users-list/users-list.component';


@NgModule({
  declarations: [
    UsersComponent,
    UserCardComponent,
    UsersListComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule
  ]
})
export class UsersModule {
}
