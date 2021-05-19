import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProjectsRoutingModule } from './projects-routing.module';
import { ProjectsComponent } from './components/projects/projects.component';
import {CommonsModule} from "../commons/commons.module";
import {ProjectsListComponent} from "./components/projects-list/projects-list.component";
import {ProjectThumbnailComponent} from "./components/project-thumbnail/project-thumbnail.component";


@NgModule({
  declarations: [
    ProjectsComponent,
    ProjectsListComponent,
    ProjectThumbnailComponent
  ],
  imports: [
    CommonModule,
    ProjectsRoutingModule,
    CommonsModule
  ]
})
export class ProjectsModule { }
