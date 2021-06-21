import {ChapterTO} from "./chapter-t-o";

export interface ProjectTO {
  name: string;
  description: string;
  chapters: ChapterTO[];
}
