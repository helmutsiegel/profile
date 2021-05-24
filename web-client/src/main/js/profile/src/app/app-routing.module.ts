import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WelcomeComponent} from "./component/welcome/welcome.component";


const routes: Routes = [
  {
    path: '',
    component: WelcomeComponent
  },
  {
    path: ':username/cv',
    loadChildren: () => import('./cv/cv.module').then(m => m.CvModule)
  },
  {
    path: ':username/resume',
    loadChildren: () => import('./resume/resume.module').then(m => m.ResumeModule)
  },
  {
    path: ':username/projects',
    loadChildren: () => import('./projects/projects.module').then(m => m.ProjectsModule)
  },
  {
    path: 'users',
    loadChildren: () => import('./users/users.module').then(m => m.UsersModule)
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
