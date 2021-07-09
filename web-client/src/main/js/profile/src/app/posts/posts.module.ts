import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostsRoutingModule } from './posts-routing.module';
import { PostsComponent } from './component/posts/posts.component';
import {CommonsModule} from "../shared/commons.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { PostCardComponent } from './component/post-card/post-card.component';
import { PostPageComponent } from './component/post-page/post-page.component';


@NgModule({
  declarations: [
    PostsComponent,
    PostCardComponent,
    PostPageComponent
  ],
    imports: [
        CommonModule,
        PostsRoutingModule,
        CommonsModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class PostsModule { }
