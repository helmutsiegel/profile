import {Injectable} from '@angular/core';
import {CvTO} from "../../shared/model/to/cv-t-o";
import {Observable} from "rxjs";
import {BackendService} from "../../shared/service/backend.service";

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
}
