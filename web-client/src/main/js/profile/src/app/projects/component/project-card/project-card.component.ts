import {Component, Input, OnInit} from '@angular/core';
import {ProjectVO} from "../../model/project-v-o";

@Component({
  selector: 'project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.css']
})
export class ProjectCardComponent implements OnInit {

  @Input()
  projectVO!: ProjectVO;

  constructor() {
  }

  ngOnInit(): void {
  }

  public openProject(): void {

  }
}
