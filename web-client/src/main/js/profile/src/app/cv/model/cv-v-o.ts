import {ExperienceVO} from "./experience-v-o";
import {LanguageVO} from "./language-v-o";
import {CertificationVO} from "./certification-v-o";

export class CvVO {

  constructor(public username: string,
              public fullName: string,
              public birthDate: string | null,
              public title: string,
              public about: string,
              public experiences: ExperienceVO[],
              public languages: LanguageVO[],
              public certifications: CertificationVO[]) {
  }
}
