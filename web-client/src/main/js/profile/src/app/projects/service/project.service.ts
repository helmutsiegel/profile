import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {CvTO} from "../../commons/model/to/cv-t-o";
import {HttpClient} from "@angular/common/http";
import {ProjectTO} from "../../commons/model/to/project-t-o";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) {
  }

  public getProjectByUsername(username: string): Observable<ProjectTO[]> {
    return this.http.get<ProjectTO[]>('project/' + username);
  }
}
