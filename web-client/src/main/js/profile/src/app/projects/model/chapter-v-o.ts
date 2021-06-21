import {SectionVO} from "./section-v-o";

export class ChapterVO {
  constructor(public title: string,
              public sections: SectionVO[]) {
  }
}
