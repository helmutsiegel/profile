import {Injectable} from '@angular/core';
import {SignUpUserTO} from "../commons/model/to/sign-up-user-t-o";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private http: HttpClient) {
  }

  public signUp(signUpUserTO: SignUpUserTO): Observable<any> {
    return this.http.post<SignUpUserTO>('user', signUpUserTO);
  }
}
