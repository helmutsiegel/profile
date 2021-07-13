import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProjectsRoutingModule} from './projects-routing.module';
import {ProjectsComponent} from './component/projects/projects.component';
import {CommonsModule} from "../shared/commons.module";
import {ProjectCardComponent} from "./component/project-card/project-card.component";
import { ProjectPageComponent } from './component/project-page/project-page.component';
import { ProjectNavigatorComponent } from './component/project-navigator/project-navigator.component';
import { NavigationElementComponent } from './component/navigation-element/navigation-element.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    ProjectsComponent,
    ProjectCardComponent,
    ProjectPageComponent,
    ProjectNavigatorComponent,
    NavigationElementComponent
  ],
    imports: [
        CommonModule,
        ProjectsRoutingModule,
        CommonsModule,
        ReactiveFormsModule,
        FormsModule
    ]
})
export class ProjectsModule {
}
