import {Component, Input, OnInit} from '@angular/core';
import {PersonalInfoVO} from "../../../resume/model/personal-info-v-o";

@Component({
  selector: 'personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {

  @Input()
  personalInfoVO!: PersonalInfoVO;

  constructor() {
  }

  ngOnInit(): void {
  }

}
