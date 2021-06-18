import {Injectable} from '@angular/core';
import {SignUpUserTO} from "../shared/model/to/sign-up-user-t-o";
import {Observable} from "rxjs";
import {BackendService} from "../shared/service/backend.service";

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
