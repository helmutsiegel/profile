import {Injectable} from '@angular/core';
import {CvTO} from "../../shared/model/to/cv-t-o";
import {CvVO} from "../model/cv-v-o";
import {UserTO} from "../../shared/model/to/user-t-o";
import {PersonalInfoVO} from "../../shared/model/vo/personal-info-v-o";
import {ExperienceVO} from "../model/experience-v-o";
import {LanguageVO} from "../model/language-v-o";
import {CertificationVO} from "../model/certification-v-o";
import {ExperienceTO} from "../../shared/model/to/experience-t-o";
import {LanguageTO} from "../../shared/model/to/language-t-o";
import {CertificationTO} from "../../shared/model/to/certification-t-o";

@Injectable({
  providedIn: 'root'
})
export class CvMapperService {

  constructor() {
  }

  public mapToVO(cvTO: CvTO): CvVO {
    return new CvVO(cvTO.about,
      this.mapPersonalInfo(cvTO.userTO),
      this.mapExperiences(cvTO.experiences),
      this.mapLanguages(cvTO.languages),
      this.mapCertifications(cvTO.certifications));
  }

  public mapExperienceVOtoTO(experienceVO: ExperienceVO): ExperienceTO {
    return {
      id: experienceVO.id,
      company: experienceVO.company,
      position: experienceVO.position,
      startDate: experienceVO.startDate,
      endDate: experienceVO.endDate
    };
  }

  public mapLanguageVOtoTO(languageVO: LanguageVO): LanguageTO {
    return {
      language: languageVO.language,
      level: languageVO.level
    };
  }

  public mapCertificationVOtoTO(certificationVO: CertificationVO): CertificationTO {
    return {
      id: certificationVO.id,
      name: certificationVO.name,
      issuedBy: certificationVO.issuedBy,
      date: certificationVO.date,
      expirationDate: certificationVO.expirationDate
    };
  }

  private mapPersonalInfo(userTO: UserTO): PersonalInfoVO {
    return new PersonalInfoVO(userTO.email, userTO.firstName + ' ' + userTO.lastName, userTO.birthDate, userTO.title);
  }

  public mapExperiences(experiences: ExperienceTO[]): ExperienceVO[] {
    return experiences.map(this.mapExperience);
  }

  private mapExperience(experienceTO: ExperienceTO): ExperienceVO {
    return new ExperienceVO(experienceTO.company, experienceTO.position,
      experienceTO.startDate, experienceTO.endDate, experienceTO.id);
  }

  public mapLanguages(languages: LanguageTO[]): LanguageVO[] {
    return languages.map(this.mapLanguage);
  }

  private mapLanguage(languageTO: LanguageTO): LanguageVO {
    return new LanguageVO(languageTO.language, languageTO.level);
  }

  public mapCertifications(certifications: CertificationTO[]): CertificationVO[] {
    return certifications.map(this.mapCertification);
  }

  private mapCertification(certificationTO: CertificationTO): CertificationVO {
    return new CertificationVO(certificationTO.id,
      certificationTO.name,
      certificationTO.issuedBy,
      certificationTO.date,
      certificationTO.expirationDate);
  }
}
