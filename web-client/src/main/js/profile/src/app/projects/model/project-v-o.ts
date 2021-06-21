import {ChapterVO} from "./chapter-v-o";

export class ProjectVO {
  constructor(public name: string,
              public chapters: ChapterVO[]) {
  }
}
