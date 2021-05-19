import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResumeRoutingModule } from './resume-routing.module';
import {ResumeComponent} from "./components/resume/resume.component";
import {CommonsModule} from "../commons/commons.module";



@NgModule({
  declarations: [
    ResumeComponent
  ],
    imports: [
        CommonModule,
        ResumeRoutingModule,
        CommonsModule
    ]
})
export class ResumeModule { }
