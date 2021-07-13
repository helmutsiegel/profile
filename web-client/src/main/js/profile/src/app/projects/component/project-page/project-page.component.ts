import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProjectVO} from "../../model/project-v-o";
import {SectionVO} from "../../model/section-v-o";
import {AuthService} from "../../../service/auth.service";
import {SimpleTextCardComponent} from "../../../shared/component/simple-text-card/simple-text-card.component";
import {CreateChapterTO} from "../../../shared/model/to/create-chapter-t-o";
import {ProjectService} from "../../service/project.service";
import {ProjectMapperService} from "../../mapping/project-mapper.service";
import {SimpleModalComponent} from "../../../shared/component/simple-modal/simple-modal.component";
import {ToastrService} from "../../../shared/service/toastr.service";

@Component({
  selector: 'project-page',
  templateUrl: './project-page.component.html',
  styleUrls: ['./project-page.component.css']
})
export class ProjectPageComponent implements OnInit {


  projectVO!: ProjectVO;
  currentSection!: SectionVO;
  seeDescription: string = 'See description +';
  closeDescription: string = 'Close description -';
  descriptionButtonLabel: string = this.seeDescription;

  @ViewChild('textCard') simpleTextCard!: SimpleTextCardComponent;
  @ViewChild('createChapterModal') createChapterModal!: SimpleModalComponent;

  constructor(public activatedRoute: ActivatedRoute,
              public authService: AuthService,
              private projectService: ProjectService,
              private projectMapper: ProjectMapperService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.forEach(data => {
      this.projectVO = data['projectVO'];
      if (this.projectVO.chapters[0] && this.projectVO.chapters[0].sections[0]) {
        this.currentSection = this.projectVO.chapters[0].sections[0];
      }
    });
  }

  public reload(): void {
    this.projectService.getByName(this.projectVO.name)
      .subscribe(projectTO => {
        const receivedProjectVO = this.projectMapper.mapToVO(projectTO);
        if (receivedProjectVO.chapters[0] && receivedProjectVO.chapters[0].sections[0]) {
          this.currentSection = receivedProjectVO.chapters[0].sections[0];
        }
        this.projectVO = receivedProjectVO;
      });
  }

  public clickDescription(): void {
    this.descriptionButtonLabel = this.descriptionButtonLabel === this.seeDescription ? this.closeDescription : this.seeDescription;
  }

  public navigateToSection(sectionVO: SectionVO): void {
    if (this.simpleTextCard.isEdited()) {
      if (window.confirm('save?')) {

      }
    }
    this.simpleTextCard.editMode = false;
    this.currentSection = sectionVO;
  }

  public createChapter(createChapterTO: CreateChapterTO): void {
    createChapterTO.projectName = this.projectVO.name;
    this.projectService.createChapter(createChapterTO)
      .subscribe(_ => {
        this.createChapterModal.close();
        this.reload();
      });
  }

  public saveSection(editedDescription: string): void {
    const currentDescription = this.currentSection.description;
    this.currentSection.description = editedDescription;
    this.projectService.updateSection({
      id: this.currentSection.id,
      title: this.currentSection.title,
      description: editedDescription
    }).subscribe(_ => {
        this.toastr.success('Section updated successfully!');
      },
      _ => {
        this.currentSection.description = currentDescription;
        this.toastr.error('Section could not save');
      })
  }
}
