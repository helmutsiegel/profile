import {Component, OnInit, ViewChild} from '@angular/core';
import {ProjectService} from "../../service/project.service";
import {ActivatedRoute} from "@angular/router";
import {ProjectVO} from "../../model/project-v-o";
import {ProjectMapperService} from "../../mapping/project-mapper.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Size} from "../../../shared/model/enum/size";
import {ProjectTO} from "../../../shared/model/to/project-t-o";
import {ToastrService} from "../../../shared/service/toastr.service";
import {SimpleModalComponent} from "../../../shared/component/simple-modal/simple-modal.component";

@Component({
  selector: 'projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  @ViewChild('newProjectModal') newProjectModal!: SimpleModalComponent;

  newProjectForm!: FormGroup;
  private projectName!: FormControl;

  projectVOs!: ProjectVO[];
  private newProjectName!: string;

  constructor(private projectService: ProjectService,
              private route: ActivatedRoute,
              private projectMapper: ProjectMapperService,
              private toastr: ToastrService) {
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
    this.projectService.createProject({name: formValues.projectName} as ProjectTO)
      .subscribe(_ => {
          this.toastr.success('Project successfully created!');
          this.newProjectModal.close();
        },
        _ => {
          this.toastr.error('Project could not create', 'Error');
        });
  }

  public get size(): typeof Size {
    return Size;
  }
}
