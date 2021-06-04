import {Injectable} from '@angular/core';
import {ResumeTO} from "../../commons/model/to/resume-t-o";
import {ResumeVO} from "../model/resume-v-o";
import {PersonalInfoVO} from "../../commons/model/vo/personal-info-v-o";
import {UserTO} from "../../commons/model/to/user-t-o";
import {ExperienceTO} from "../../commons/model/to/experience-t-o";
import {ExperienceVO} from "../model/experience-v-o";
import {LanguageTO} from "../../commons/model/to/language-t-o";
import {LanguageVO} from "../model/language-v-o";
import {CertificationTO} from "../../commons/model/to/certification-t-o";
import {CertificationVO} from "../model/certification-v-o";

@Injectable({
  providedIn: 'root'
})
export class ResumeMapperService {

  constructor() {
  }

  public mapToVO(resumeTO: ResumeTO): ResumeVO {
    return new ResumeVO(resumeTO.about,
      this.mapPersonalInfo(resumeTO.userTO),
      this.mapExperiences(resumeTO.experiences),
      this.mapLanguages(resumeTO.languages),
      this.mapCertifications(resumeTO.certifications));
  }

  private mapPersonalInfo(userTO: UserTO): PersonalInfoVO {
    return new PersonalInfoVO(userTO.username, userTO.firstName + ' ' + userTO.lastName, userTO.birthDate, userTO.title);
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
}
