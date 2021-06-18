import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ProjectTO} from "../../commons/model/to/project-t-o";
import {BackendService} from "../../commons/service/backend.service";

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
}
