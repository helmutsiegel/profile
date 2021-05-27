import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProjectsRoutingModule } from './projects-routing.module';
import { ProjectsComponent } from './component/projects/projects.component';
import {CommonsModule} from "../commons/commons.module";
import {ProjectsListComponent} from "./component/projects-list/projects-list.component";
import {ProjectThumbnailComponent} from "./component/project-thumbnail/project-thumbnail.component";
import { ProjectCardComponent } from './component/project-card/project-card.component';


@NgModule({
  declarations: [
    ProjectsComponent,
    ProjectsListComponent,
    ProjectThumbnailComponent,
    ProjectCardComponent
  ],
  imports: [
    CommonModule,
    ProjectsRoutingModule,
    CommonsModule
  ]
})
export class ProjectsModule { }
