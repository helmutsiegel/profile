import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CvComponent} from "./component/cv/cv.component";
import {CvResolverService} from "./service/cv-resolver.service";

const routes: Routes = [
  {
    path: '',
    component: CvComponent,
    resolve: {cvVO: CvResolverService}
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CvRoutingModule {
}
