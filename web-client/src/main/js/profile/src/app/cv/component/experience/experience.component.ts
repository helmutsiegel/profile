import {Component, Input, OnInit} from '@angular/core';
import {ExperienceVO} from "../../model/experience-v-o";

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  @Input()
  public experienceVO!: ExperienceVO;

  constructor() { }

  ngOnInit(): void {
  }

}
