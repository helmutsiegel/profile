import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostsRoutingModule } from './posts-routing.module';
import { PostsComponent } from './component/posts/posts.component';
import {CommonsModule} from "../shared/commons.module";
import {ReactiveFormsModule} from "@angular/forms";
import { PostCardComponent } from './component/post-card/post-card.component';


@NgModule({
  declarations: [
    PostsComponent,
    PostCardComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    CommonsModule,
    ReactiveFormsModule
  ]
})
export class PostsModule { }
