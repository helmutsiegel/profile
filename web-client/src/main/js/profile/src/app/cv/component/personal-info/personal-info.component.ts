import {Component, Input, OnInit} from '@angular/core';
import {CvVO} from "../../model/cv-v-o";

@Component({
  selector: 'personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {

  @Input()
  cvVO!: CvVO;

  constructor() {
  }

  ngOnInit(): void {
  }

}
