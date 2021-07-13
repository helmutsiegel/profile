import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ProjectTO} from "../../shared/model/to/project-t-o";
import {BackendService} from "../../shared/service/backend.service";
import {CreateChapterTO} from "../../shared/model/to/create-chapter-t-o";
import {SectionTO} from "../../shared/model/to/section-t-o";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private backend: BackendService) {
  }

  public getProjectByEmail(email: string): Observable<ProjectTO[]> {
    return this.backend.get<ProjectTO[]>('project/byEmail/' + email);
  }

  public getByName(name: string): Observable<ProjectTO> {
    return this.backend.get<ProjectTO>('project/' + name);
  }

  public createProject(projectTO: ProjectTO): Observable<any> {
    return this.backend.post('project', projectTO);
  }

  public createChapter(createChapterTO: CreateChapterTO): Observable<any> {
    return this.backend.post('project/chapter', createChapterTO);
  }

  public updateSection(sectionTO: SectionTO): Observable<any> {
    return this.backend.put('project/section', sectionTO);
  }
}
