import {ChapterVO} from "./chapter-v-o";
import {PersonalInfoVO} from "../../shared/model/vo/personal-info-v-o";

export class ProjectVO {
  constructor(public name: string,
              public description: string,
              public fullName: string,
              public chapters: ChapterVO[]) {
  }
}
