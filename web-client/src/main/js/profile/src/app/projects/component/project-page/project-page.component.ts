import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProjectVO} from "../../model/project-v-o";
import {SectionVO} from "../../model/section-v-o";
import {AuthService} from "../../../service/auth.service";
import {SimpleTextCardComponent} from "../../../shared/component/simple-text-card/simple-text-card.component";

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

  constructor(public activatedRoute: ActivatedRoute,
              public authService: AuthService) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.forEach(data => {
      this.projectVO = data['projectVO'];
      this.currentSection = this.projectVO.chapters[0].sections[0];
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
}
