import {PersonalInfoVO} from "../../shared/model/vo/personal-info-v-o";
import {ExperienceVO} from "./experience-v-o";
import {LanguageVO} from "./language-v-o";
import {CertificationVO} from "./certification-v-o";

export class ResumeVO {
  constructor(public about: string,
              public personalInfoVO: PersonalInfoVO,
              public experiences: ExperienceVO[],
              public languages: LanguageVO[],
              public certifications: CertificationVO[]) {
  }
}
