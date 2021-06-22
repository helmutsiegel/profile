import {Component, OnInit} from '@angular/core';
import {ProjectService} from "../../service/project.service";
import {ActivatedRoute} from "@angular/router";
import {ProjectVO} from "../../model/project-v-o";
import {ProjectMapperService} from "../../mapping/project-mapper.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Size} from "../../../shared/model/enum/size";

@Component({
  selector: 'projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  newProjectForm!: FormGroup;
  private projectName!: FormControl;

  projectVOs!: ProjectVO[];
  private newProjectName!: string;

  constructor(private projectService: ProjectService,
              private route: ActivatedRoute,
              private projectMapper: ProjectMapperService) {
  }

  ngOnInit(): void {
    const emailFromUrl = this.route.snapshot.params['email'];
    this.projectService.getProjectByEmail(emailFromUrl).subscribe(projects => {
      this.projectVOs = projects.map(projectTO => this.projectMapper.mapToVO(projectTO));
    });

    this.projectName = new FormControl(this.newProjectName, Validators.required);
    this.newProjectForm = new FormGroup({
      projectName: this.projectName
    })
  }

  public newProject(formValues: any): void {
    console.log(formValues);
  }

  public get size(): typeof Size {
    return Size;
  }
}
