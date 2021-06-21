import {Injectable} from '@angular/core';
import {ProjectTO} from "../../shared/model/to/project-t-o";
import {ProjectVO} from "../model/project-v-o";
import {ChapterTO} from "../../shared/model/to/chapter-t-o";
import {ChapterVO} from "../model/chapter-v-o";
import {SectionTO} from "../../shared/model/to/section-t-o";
import {SectionVO} from "../model/section-v-o";

@Injectable({
  providedIn: 'root'
})
export class ProjectMapperService {

  constructor() {
  }

  public mapToVO(projectTO: ProjectTO): ProjectVO {
    const userTO = projectTO.userTO;
    return new ProjectVO(projectTO.name, projectTO.description,
      userTO.firstName + ' ' + userTO.lastName, this.mapChaptersToVO(projectTO.chapters));
  }

  private mapChaptersToVO(chapterTOs: ChapterTO[]): ChapterVO[] {
    return chapterTOs.map(chapterTO => this.mapChapterToVO(chapterTO));
  }

  private mapChapterToVO(chapterTO: ChapterTO): ChapterVO {
    return new ChapterVO(chapterTO.title, this.mapSectionsToVO(chapterTO.sections));
  }

  private mapSectionsToVO(sectionTOs: SectionTO[]): SectionVO[] {
    return sectionTOs.map(sectionTO => this.mapSectionToVO(sectionTO));
  }

  private mapSectionToVO(sectionTO: SectionTO): SectionVO {
    return new SectionVO(sectionTO.title, sectionTO.description);
  }
}
