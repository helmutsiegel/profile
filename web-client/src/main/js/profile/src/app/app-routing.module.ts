import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WelcomeComponent} from "./component/welcome/welcome.component";
import {Error404Component} from "./shared/component/error404/error404.component";
import {UsersPageRouteActivatorService} from "./shared/service/users-page-route-activator.service";
import {LoginComponent} from "./component/login/login.component";
import {SignUpComponent} from "./component/sign-up/sign-up.component";
import {UserLoggedInGuard} from "./shared/guard/user-logged-in.guard";
import {UserNotLoggedInGuard} from "./shared/guard/user-not-logged-in.guard";


const routes: Routes = [
  {
    path: '',
    component: WelcomeComponent
  },
  {
    path: 'user/login',
    component: LoginComponent,
    canActivate: [UserNotLoggedInGuard]
  },
  {
    path: 'user/signup',
    component: SignUpComponent,
    canActivate: [UserNotLoggedInGuard]
  },
  {
    path: 'user/profile',
    canActivate: [UserLoggedInGuard],
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
    loadChildren: () => import('./user/users.module').then(m => m.UsersModule)
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
