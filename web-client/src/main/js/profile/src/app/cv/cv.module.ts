import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CvRoutingModule} from './cv-routing.module';
import {CvComponent} from './component/cv/cv.component';
import {CommonsModule} from "../commons/commons.module";
import { ExperiencesComponent } from './component/experiences/experiences.component';
import { PersonalInfoComponent } from './component/personal-info/personal-info.component';
import { LanguagesComponent } from './component/languages/languages.component';
import { CertificationsComponent } from './component/certifications/certifications.component';


@NgModule({
  declarations: [
    CvComponent,
    ExperiencesComponent,
    PersonalInfoComponent,
    LanguagesComponent,
    CertificationsComponent
  ],
  imports: [
    CommonModule,
    CvRoutingModule,
    CommonsModule
  ]
})
export class CvModule {
}
