import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'settings-navigator',
  templateUrl: './settings-navigator.component.html',
  styleUrls: ['./settings-navigator.component.css']
})
export class SettingsNavigatorComponent implements OnInit {

  selectedButtonName: string = 'personalData';

  constructor() {
  }

  ngOnInit(): void {
  }

  public getCSSClassForButton(buttonName: string) {
    return buttonName == this.selectedButtonName ? 'btn-dark' : 'btn-light btn-outline-secondary';
  }

  public navigate(buttonName: string): void {
    this.selectedButtonName = buttonName;
  }
}
