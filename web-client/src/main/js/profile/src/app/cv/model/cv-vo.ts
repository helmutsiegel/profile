import {ExperienceVO} from "./experience-v-o";

export class CvVo {
  constructor(public username: string,
              public fullName: string,
              public birthDate: string | null,
              public title: string,
              public about: string,
              public experiences: ExperienceVO[]) {
  }
}
