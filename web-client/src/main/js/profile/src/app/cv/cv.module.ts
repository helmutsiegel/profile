import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CvRoutingModule} from './cv-routing.module';
import {CvComponent} from './component/cv/cv.component';
import {CommonsModule} from "../commons/commons.module";
import { ExperiencesComponent } from './component/experiences/experiences.component';
import { PersonalInfoComponent } from './component/personal-info/personal-info.component';


@NgModule({
  declarations: [
    CvComponent,
    ExperiencesComponent,
    PersonalInfoComponent
  ],
  imports: [
    CommonModule,
    CvRoutingModule,
    CommonsModule
  ]
})
export class CvModule {
}
