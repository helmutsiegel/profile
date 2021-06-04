import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {CvTO} from "../../commons/model/to/cv-t-o";
import {HttpClient} from "@angular/common/http";
import {ResumeTO} from "../../commons/model/to/resume-t-o";

@Injectable({
  providedIn: 'root'
})
export class ResumeService {

  constructor(private http: HttpClient) {
  }

  public getResumeByUsername(username: string): Observable<ResumeTO> {
    return this.http.get<ResumeTO>('resume/' + username);
  }
}
