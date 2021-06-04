import {ExperienceVO} from "./experience-v-o";
import {LanguageVO} from "./language-v-o";
import {CertificationVO} from "./certification-v-o";
import {PersonalInfoVO} from "../../resume/model/personal-info-v-o";

export class CvVO {

  constructor(public about: string,
              public personalInfoVO: PersonalInfoVO,
              public experiences: ExperienceVO[],
              public languages: LanguageVO[],
              public certifications: CertificationVO[]) {
  }
}
