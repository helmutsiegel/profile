import {Injectable} from '@angular/core';
import {ResumeTO} from "../../commons/model/to/resume-t-o";
import {ResumeVO} from "../model/resume-v-o";
import {PersonalInfoVO} from "../model/personal-info-v-o";
import {UserTO} from "../../commons/model/to/user-t-o";

@Injectable({
  providedIn: 'root'
})
export class ResumeMapperService {

  constructor() {
  }

  public mapToVO(resumeTO: ResumeTO): ResumeVO {
    return new ResumeVO(resumeTO.about, this.mapPersonalInfo(resumeTO.userTO));
  }

  private mapPersonalInfo(userTO: UserTO): PersonalInfoVO {
    return new PersonalInfoVO(userTO.username, userTO.firstName + ' ' + userTO.lastName, userTO.birthDate, userTO.title);
  }
}
