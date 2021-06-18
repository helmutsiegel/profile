import {UserTO} from "./user-t-o";
import {LanguageTO} from "./language-t-o";
import {ExperienceTO} from "./experience-t-o";
import {CertificationTO} from "./certification-t-o";

export interface CvTO {
  about: string;
  userTO: UserTO;
  languages: LanguageTO[];
  experiences: ExperienceTO[];
  certifications: CertificationTO[]
}
