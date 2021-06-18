import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WelcomeComponent} from "./component/welcome/welcome.component";
import {Error404Component} from "./commons/component/error404/error404.component";
import {UsersPageRouteActivatorService} from "./commons/service/users-page-route-activator.service";
import {LoginComponent} from "./component/login/login.component";
import {LoginPageRouteActivatorService} from "./commons/service/login-page-route-activator.service";
import {SignUpComponent} from "./component/sign-up/sign-up.component";


const routes: Routes = [
  {
    path: '',
    component: WelcomeComponent
  },
  {
    path: 'user/login',
    component: LoginComponent,
    canActivate: [LoginPageRouteActivatorService]
  },
  {
    path: 'user/signup',
    component: SignUpComponent,
  },
  {
    path: 'user/profile',
    loadChildren: () => import('./user-profile/user-profile.module').then(m => m.UserProfileModule)
  },
  {
    path: ':email/cv',
    loadChildren: () => import('./cv/cv.module').then(m => m.CvModule),
    canActivate: [UsersPageRouteActivatorService]
  },
  {
    path: ':email/resume',
    loadChildren: () => import('./resume/resume.module').then(m => m.ResumeModule),
    canActivate: [UsersPageRouteActivatorService]
  },
  {
    path: ':email/projects',
    loadChildren: () => import('./projects/projects.module').then(m => m.ProjectsModule),
    canActivate: [UsersPageRouteActivatorService]
  },
  {
    path: ':email/:',
    redirectTo: ':email/cv'
  },
  {
    path: 'users',
    loadChildren: () => import('./users/users.module').then(m => m.UsersModule)
  },
  {
    path: '404',
    component: Error404Component
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
