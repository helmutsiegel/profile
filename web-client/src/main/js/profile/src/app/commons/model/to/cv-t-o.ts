import {UserTO} from "./user-t-o";
import {ExperienceTO} from "./experience-t-o";
import {CertificationTO} from "./certification-t-o";
import {LanguageTO} from "./language-t-o";

export interface CvTO {
  about: string;
  userTO: UserTO;
  experiences: ExperienceTO[];
  certifications: CertificationTO[];
  languages: LanguageTO[];
}
