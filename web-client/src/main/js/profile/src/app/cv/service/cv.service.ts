import {Injectable} from '@angular/core';
import {CvTO} from "../../commons/model/to/cv-t-o";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CvService {

  constructor(private http: HttpClient) {
  }

  public getCvByUsername(username: string): Observable<CvTO> {
    return this.http.get<CvTO>('cv/' + username);
  }
}
