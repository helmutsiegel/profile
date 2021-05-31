import {UserTO} from "./user-t-o";

export interface SignUpUserTO extends UserTO{
  password1: string
  password2: string
}
