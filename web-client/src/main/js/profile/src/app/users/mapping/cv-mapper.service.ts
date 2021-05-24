import {Injectable} from '@angular/core';
import {CvTo} from "../../commons/model/to/cv-to";
import {CvVo} from "../../cv/model/cv-vo";

@Injectable({
  providedIn: 'root'
})
export class CvMapperService {

  constructor() {
  }

  public mapToVO(cvTo: CvTo): CvVo {
    return new CvVo(cvTo.userTO.userName,
      cvTo.userTO.firstName + ' ' + cvTo.userTO.lastName);
  }
}
