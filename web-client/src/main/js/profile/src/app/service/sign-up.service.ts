import {Injectable} from '@angular/core';
import {SignUpUserTO} from "../commons/model/to/sign-up-user-t-o";
import {Observable} from "rxjs";
import {BackendService} from "../commons/service/backend.service";

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private backend: BackendService) {
  }

  public signUp(signUpUserTO: SignUpUserTO): Observable<any> {
    return this.backend.post<SignUpUserTO>('user', signUpUserTO);
  }
}
