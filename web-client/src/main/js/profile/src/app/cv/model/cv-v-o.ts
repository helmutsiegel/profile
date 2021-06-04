import {PersonalInfoVO} from "../../commons/model/vo/personal-info-v-o";

export class CvVO {

  constructor(public about: string,
              public personalInfoVO: PersonalInfoVO) {
  }
}
