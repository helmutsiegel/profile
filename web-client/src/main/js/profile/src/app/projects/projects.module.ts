import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProjectsRoutingModule} from './projects-routing.module';
import {ProjectsComponent} from './component/projects/projects.component';
import {CommonsModule} from "../shared/commons.module";
import {ProjectCardComponent} from "./component/project-card/project-card.component";
import { ProjectPageComponent } from './component/project-page/project-page.component';
import { ProjectNavigatorComponent } from './component/project-navigator/project-navigator.component';

@NgModule({
  declarations: [
    ProjectsComponent,
    ProjectCardComponent,
    ProjectPageComponent,
    ProjectNavigatorComponent
  ],
  imports: [
    CommonModule,
    ProjectsRoutingModule,
    CommonsModule
  ]
})
export class ProjectsModule {
}
