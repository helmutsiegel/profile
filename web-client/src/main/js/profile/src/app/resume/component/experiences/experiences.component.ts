import {Component, Input, OnInit} from '@angular/core';
import {ExperienceVO} from "../../model/experience-v-o";

@Component({
  selector: 'experiences',
  templateUrl: './experiences.component.html',
  styleUrls: ['./experiences.component.css']
})
export class ExperiencesComponent implements OnInit {

  @Input()
  public experiences!: ExperienceVO[];

  constructor() {
  }

  ngOnInit(): void {
  }

}
