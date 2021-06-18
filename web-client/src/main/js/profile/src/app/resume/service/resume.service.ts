import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ResumeTO} from "../../shared/model/to/resume-t-o";
import {BackendService} from "../../shared/service/backend.service";

@Injectable({
  providedIn: 'root'
})
export class ResumeService {

  constructor(private backend: BackendService) {
  }

  public getResumeByEmail(email: string): Observable<ResumeTO> {
    return this.backend.get<ResumeTO>('resume/' + email);
  }

  public update(resumeTO: ResumeTO): Observable<any> {
    return this.backend.put('resume', resumeTO, true);
  }
}
