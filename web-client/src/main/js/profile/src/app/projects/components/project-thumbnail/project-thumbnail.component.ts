import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'project-thumbnail',
  templateUrl: './project-thumbnail.component.html',
  styleUrls: ['./project-thumbnail.component.css']
})
export class ProjectThumbnailComponent implements OnInit {
  @Input() project: any;
  @Output() eventClick: EventEmitter<string> = new EventEmitter();

  constructor() {
  }

  ngOnInit(): void {
  }

  handleClick() {
    this.eventClick.emit(this.project.name);
  }

  logFoo() {
    console.log('foo');
  }
}
