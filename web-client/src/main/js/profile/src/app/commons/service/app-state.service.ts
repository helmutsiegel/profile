import {Injectable, OnInit} from '@angular/core';
import {AppState} from "../model/app-state";
import {UserTo} from "../../users/model/user-to";

@Injectable({
  providedIn: 'root'
})
export class AppStateService {
  private appState!: AppState;

  constructor() {
  }

  public getSelectedUsername(): string {
    return this.appState?.userTO?.userName;
  }

  public setSelectedUser(userTo: UserTo) {
    this.appState = {...this.appState, userTO: userTo }
  }
}
