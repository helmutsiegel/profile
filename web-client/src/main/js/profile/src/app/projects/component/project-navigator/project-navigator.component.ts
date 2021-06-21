import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ChapterVO} from "../../model/chapter-v-o";
import {SectionVO} from "../../model/section-v-o";

@Component({
  selector: 'project-navigator',
  templateUrl: './project-navigator.component.html',
  styleUrls: ['./project-navigator.component.css']
})
export class ProjectNavigatorComponent implements OnInit {

  @Input() chapters!: ChapterVO[];
  @Output() onClick: EventEmitter<SectionVO> = new EventEmitter<SectionVO>()

  constructor() { }

  ngOnInit(): void {
  }

}
