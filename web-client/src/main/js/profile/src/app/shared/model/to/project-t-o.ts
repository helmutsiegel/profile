import {ChapterTO} from "./chapter-t-o";
import {UserTO} from "./user-t-o";

export interface ProjectTO {
  name: string;
  description: string;
  userTO: UserTO;
  chapters: ChapterTO[];
}
