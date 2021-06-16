import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProjectsComponent} from "./component/projects/projects.component";
import {ProjectPageComponent} from "./component/project-page/project-page.component";
import {ProjectResolver} from "./resolver/project.resolver";


const routes: Routes = [
  {
    path: '',
    component: ProjectsComponent
  },
  {
    path: ':projectName',
    component: ProjectPageComponent,
    resolve: {projectVO: ProjectResolver}
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectsRoutingModule {
}
