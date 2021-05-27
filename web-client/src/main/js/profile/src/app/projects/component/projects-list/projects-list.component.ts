import { Component, OnInit } from '@angular/core';
import {ProjectTO} from "../../../commons/model/to/project-t-o";


@Component({
  selector: 'projects-list',
  templateUrl: './projects-list.component.html',
  styleUrls: ['./projects-list.component.css']
})
export class ProjectsListComponent implements OnInit {

  project: ProjectTO = {
    name: 'Profile'
  }

  constructor() { }

  ngOnInit(): void {
  }

  handleEventClick(event: string) {
    console.log(event);
  }
}
