import {UserTO} from "./user-t-o";
import {ExperienceTO} from "../../../users/model/experience-t-o";

export interface CvTo {
  about: string;
  userTO: UserTO;
  experiences: ExperienceTO[]
}
