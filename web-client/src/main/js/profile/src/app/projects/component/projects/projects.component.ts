import {Component, OnInit} from '@angular/core';
import {ProjectService} from "../../service/project.service";
import {ActivatedRoute} from "@angular/router";
import {ProjectVO} from "../../model/project-v-o";

@Component({
  selector: 'projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  projectVOs!: ProjectVO[];

  constructor(private projectService: ProjectService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    const usernameFromUrl = this.route.snapshot.params['username'];
    this.projectService.getProjectByUsername(usernameFromUrl).subscribe(projects => {
      this.projectVOs = projects;
    });
  }
}
