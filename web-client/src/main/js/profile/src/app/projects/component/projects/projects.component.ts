import {Component, OnInit} from '@angular/core';
import {ProjectService} from "../../service/project.service";
import {ActivatedRoute} from "@angular/router";
import {ProjectVO} from "../../model/project-v-o";
import {ProjectMapperService} from "../../mapping/project-mapper.service";

@Component({
  selector: 'projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  projectVOs!: ProjectVO[];

  constructor(private projectService: ProjectService,
              private route: ActivatedRoute,
              private projectMapper: ProjectMapperService) {
  }

  ngOnInit(): void {
    const emailFromUrl = this.route.snapshot.params['email'];
    this.projectService.getProjectByEmail(emailFromUrl).subscribe(projects => {
      this.projectVOs = projects.map(projectTO => this.projectMapper.mapToVO(projectTO));
    });
  }
}
