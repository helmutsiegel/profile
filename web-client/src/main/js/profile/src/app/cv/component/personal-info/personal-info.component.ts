import {Component, Input, OnInit} from '@angular/core';
import {CvVo} from "../../model/cv-vo";

@Component({
  selector: 'personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {

  @Input()
  cvVO!: CvVo;

  constructor() {
  }

  ngOnInit(): void {
  }

}
