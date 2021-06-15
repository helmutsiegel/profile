import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {CvTO} from "../../commons/model/to/cv-t-o";
import {HttpClient} from "@angular/common/http";
import {ProjectTO} from "../../commons/model/to/project-t-o";
import {BackendService} from "../../commons/service/backend.service";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private backend: BackendService) {
  }

  public getProjectByUsername(username: string): Observable<ProjectTO[]> {
    return this.backend.get<ProjectTO[]>('project/' + username);
  }
}
