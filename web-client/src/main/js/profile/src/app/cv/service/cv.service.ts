import {Injectable} from '@angular/core';
import {CvTO} from "../../commons/model/to/cv-t-o";
import {Observable} from "rxjs";
import {BackendService} from "../../commons/service/backend.service";

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
