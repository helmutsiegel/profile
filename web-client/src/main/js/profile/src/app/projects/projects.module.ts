import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProjectsRoutingModule} from './projects-routing.module';
import {ProjectsComponent} from './component/projects/projects.component';
import {CommonsModule} from "../commons/commons.module";
import {ProjectCardComponent} from "./component/project-card/project-card.component";
import { ProjectPageComponent } from './component/project-page/project-page.component';

@NgModule({
  declarations: [
    ProjectsComponent,
    ProjectCardComponent,
    ProjectPageComponent
  ],
  imports: [
    CommonModule,
    ProjectsRoutingModule,
    CommonsModule
  ]
})
export class ProjectsModule {
}
