import {Injectable} from '@angular/core';
import {CvTo} from "../../commons/model/to/cv-to";
import {CvVo} from "../../cv/model/cv-vo";
import {ExperienceTO} from "../model/experience-t-o";
import {ExperienceVO} from "../../cv/model/experience-v-o";

@Injectable({
  providedIn: 'root'
})
export class CvMapperService {

  constructor() {
  }

  public mapToVO(cvTo: CvTo): CvVo {
    const userTO = cvTo.userTO;
    return new CvVo(userTO.userName,
      userTO.firstName + ' ' + userTO.lastName,
      userTO.birthDate,
      userTO.title,
      cvTo.about,
      this.mapExperiences(cvTo));
  }

  private mapExperiences(cvTo: CvTo): ExperienceVO[] {
    return cvTo.experiences.map(experience => this.mapExperience(experience));
  }

  private mapExperience(experienceTO: ExperienceTO): ExperienceVO {
    return new ExperienceVO(experienceTO.company, experienceTO.startDate, experienceTO.endDate);
  }
}
