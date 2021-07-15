import {Injectable} from '@angular/core';
import {CvTO} from "../../shared/model/to/cv-t-o";
import {Observable} from "rxjs";
import {BackendService} from "../../shared/service/backend.service";
import {ExperienceTO} from "../../shared/model/to/experience-t-o";
import {LanguageTO} from "../../shared/model/to/language-t-o";

@Injectable({
  providedIn: 'root'
})
export class CvService {

  constructor(private backend: BackendService) {
  }

  public getByEmail(email: string): Observable<CvTO> {
    return this.backend.get<CvTO>('cv/' + email);
  }

  public update(cvTO: CvTO): void {
    this.backend.put('cv', cvTO).subscribe();
  }

  public updateExperiences(experiences: ExperienceTO[]): Observable<ExperienceTO[]> {
    return this.backend.put('cv/experiences', experiences);
  }

  public updateLanguages(languageTOS: LanguageTO[]): Observable<LanguageTO[]> {
    return this.backend.put('cv/languages', languageTOS);
  }
}
