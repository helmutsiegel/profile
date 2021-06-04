import {PersonalInfoVO} from "./personal-info-v-o";

export class ResumeVO {
  constructor(public about: string,
              public personalInfoVO: PersonalInfoVO) {
  }
}
