import {Injectable} from '@angular/core';
import {CvTO} from "../../commons/model/to/cv-t-o";
import {CvVO} from "../model/cv-v-o";
import {ExperienceTO} from "../../commons/model/to/experience-t-o";
import {ExperienceVO} from "../model/experience-v-o";
import {LanguageVO} from "../model/language-v-o";
import {LanguageTO} from "../../commons/model/to/language-t-o";
import {CertificationTO} from "../../commons/model/to/certification-t-o";
import {CertificationVO} from "../model/certification-v-o";
import {UserTO} from "../../commons/model/to/user-t-o";
import {PersonalInfoVO} from "../../resume/model/personal-info-v-o";

@Injectable({
  providedIn: 'root'
})
export class CvMapperService {

  constructor() {
  }

  public mapToVO(cvTO: CvTO): CvVO {
    const userTO = cvTO.userTO;
    return new CvVO(cvTO.about,
      this.mapPersonalInfo(cvTO.userTO),
      this.mapExperiences(cvTO.experiences),
      this.mapLanguages(cvTO.languages),
      this.mapCertifications(cvTO.certifications));
  }

  private mapExperiences(experiences: ExperienceTO[]): ExperienceVO[] {
    return experiences.map(this.mapExperience);
  }

  private mapExperience(experienceTO: ExperienceTO): ExperienceVO {
    return new ExperienceVO(experienceTO.company, experienceTO.startDate, experienceTO.endDate, experienceTO.position);
  }

  private mapLanguages(languages: LanguageTO[]): LanguageVO[] {
    return languages.map(this.mapLanguage);
  }

  private mapLanguage(languageTO: LanguageTO): LanguageVO {
    return new LanguageVO(languageTO.language, languageTO.level);
  }

  private mapCertifications(certifications: CertificationTO[]): CertificationVO[] {
    return certifications.map(this.mapCertification)
  }

  private mapCertification(certificationTO: CertificationTO): CertificationVO {
    return new CertificationVO(certificationTO.name, certificationTO.issuedBy, certificationTO.date, certificationTO.expirationDate);
  }

  private mapPersonalInfo(userTO: UserTO): PersonalInfoVO {
    return new PersonalInfoVO(userTO.username, userTO.firstName + ' ' + userTO.lastName, userTO.birthDate, userTO.title);
  }
}
