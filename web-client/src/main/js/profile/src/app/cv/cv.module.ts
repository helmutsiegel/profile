import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CvRoutingModule} from './cv-routing.module';
import {CvComponent} from './components/cv/cv.component';
import {CommonsModule} from "../commons/commons.module";


@NgModule({
  declarations: [
    CvComponent
  ],
  imports: [
    CommonModule,
    CvRoutingModule,
    CommonsModule
  ]
})
export class CvModule {
}
