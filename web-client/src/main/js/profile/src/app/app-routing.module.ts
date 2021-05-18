import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {WelcomeComponent} from "./home/welcome/welcome.component";
import {CvComponent} from "./cv/cv/cv.component";
import {ResumeComponent} from "./resume/resume/resume.component";
import {ProjectsListComponent} from "./projects-list/projects-list.component";

const routes: Routes = [
  {
    path: '',
    component: WelcomeComponent
  },
  {
    path: 'cv',
    component: CvComponent
  },
  {
    path: 'resume',
    component: ResumeComponent
  },
  {
    path: 'projects',
    component: ProjectsListComponent
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
