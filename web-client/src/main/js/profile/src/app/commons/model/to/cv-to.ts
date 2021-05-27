import {UserTo} from "../../../users/model/user-to";
import {ExperienceTO} from "../../../users/model/experience-t-o";

export interface CvTo {
  about: string;
  userTO: UserTo;
  experiences: ExperienceTO[]
}
