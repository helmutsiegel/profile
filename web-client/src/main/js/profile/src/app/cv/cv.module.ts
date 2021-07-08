import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CvRoutingModule} from './cv-routing.module';
import {CvComponent} from './component/cv/cv.component';
import {CommonsModule} from "../shared/commons.module";
import { ExperiencesComponent } from './component/experiences/experiences.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    CvComponent,
    ExperiencesComponent
  ],
  exports: [
  ],
    imports: [
        CommonModule,
        CvRoutingModule,
        CommonsModule,
        FormsModule
    ]
})
export class CvModule {
}
