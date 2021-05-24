import {Injectable} from '@angular/core';
import {CvTo} from "../../commons/model/to/cv-to";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CvService {

  constructor(private http: HttpClient) {
  }

  public getCvByUsername(username: string): Observable<CvTo> {
    return this.http.get<CvTo>('/rs/cv/' + username);
  }
}
