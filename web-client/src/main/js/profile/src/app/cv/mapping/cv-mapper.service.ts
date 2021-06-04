import {Injectable} from '@angular/core';
import {CvTO} from "../../commons/model/to/cv-t-o";
import {CvVO} from "../model/cv-v-o";
import {UserTO} from "../../commons/model/to/user-t-o";
import {PersonalInfoVO} from "../../commons/model/vo/personal-info-v-o";

@Injectable({
  providedIn: 'root'
})
export class CvMapperService {

  constructor() {
  }

  public mapToVO(cvTO: CvTO): CvVO {
    return new CvVO(cvTO.about,
      this.mapPersonalInfo(cvTO.userTO));
  }

  private mapPersonalInfo(userTO: UserTO): PersonalInfoVO {
    return new PersonalInfoVO(userTO.username, userTO.firstName + ' ' + userTO.lastName, userTO.birthDate, userTO.title);
  }
}
