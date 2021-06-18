import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ResumeRoutingModule} from './resume-routing.module';
import {ResumeComponent} from "./component/resume/resume.component";
import {CommonsModule} from "../shared/commons.module";
import {CvModule} from "../cv/cv.module";
import {ExperiencesComponent} from "./component/experiences/experiences.component";
import {LanguagesComponent} from "./component/languages/languages.component";
import {CertificationsComponent} from "./component/certifications/certifications.component";


@NgModule({
  declarations: [
    ResumeComponent,
    ExperiencesComponent,
    LanguagesComponent,
    CertificationsComponent
  ],
  imports: [
    CommonModule,
    ResumeRoutingModule,
    CommonsModule,
    CvModule
  ]
})
export class ResumeModule {
}
