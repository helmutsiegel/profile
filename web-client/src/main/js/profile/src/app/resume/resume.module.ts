import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResumeRoutingModule } from './resume-routing.module';
import {ResumeComponent} from "./component/resume/resume.component";
import {CommonsModule} from "../commons/commons.module";
import {CvModule} from "../cv/cv.module";



@NgModule({
  declarations: [
    ResumeComponent
  ],
    imports: [
        CommonModule,
        ResumeRoutingModule,
        CommonsModule,
        CvModule
    ]
})
export class ResumeModule { }
