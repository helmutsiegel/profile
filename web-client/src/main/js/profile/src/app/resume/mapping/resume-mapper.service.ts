import { Injectable } from '@angular/core';
import {CvTO} from "../../commons/model/to/cv-t-o";
import {CvVO} from "../../cv/model/cv-v-o";
import {ResumeTO} from "../../commons/model/to/resume-t-o";
import {ResumeVO} from "../model/resume-v-o";

@Injectable({
  providedIn: 'root'
})
export class ResumeMapperService {

  constructor() { }

  public mapToVO(resumeTO: ResumeTO): ResumeVO {
    const userTO = resumeTO.userTO;
    return new ResumeVO(userTO.username,
      userTO.firstName + ' ' + userTO.lastName,
      userTO.birthDate,
      userTO.title,
      resumeTO.about);
  }
}
