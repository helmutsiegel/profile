import { Component, OnInit } from '@angular/core';
import {ProjectTO} from "../model/project-to";

@Component({
  selector: 'app-projects-list',
  templateUrl: './projects-list.component.html',
  styleUrls: ['./projects-list.component.css']
})
export class ProjectsListComponent implements OnInit {

  project: ProjectTO = {
    id:1,
    name: 'Profile',
    imageUrl: '/assets/images/profile.PNG'
  }

  constructor() { }

  ngOnInit(): void {
  }

  handleEventClick(event: string) {
    console.log(event);
  }
}
