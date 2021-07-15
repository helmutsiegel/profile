import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CvRoutingModule} from './cv-routing.module';
import {CvComponent} from './component/cv/cv.component';
import {CommonsModule} from "../shared/commons.module";
import { ExperiencesComponent } from './component/experiences/experiences.component';
import {FormsModule} from "@angular/forms";
import { LanguagesComponent } from './component/languages/languages.component';


@NgModule({
  declarations: [
    CvComponent,
    ExperiencesComponent,
    LanguagesComponent
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
