import {Component, Input, OnInit} from '@angular/core';
import {ChapterVO} from "../../model/chapter-v-o";

@Component({
  selector: 'project-navigator',
  templateUrl: './project-navigator.component.html',
  styleUrls: ['./project-navigator.component.css']
})
export class ProjectNavigatorComponent implements OnInit {

  @Input() chapters!: ChapterVO[];

  constructor() { }

  ngOnInit(): void {
  }

}
