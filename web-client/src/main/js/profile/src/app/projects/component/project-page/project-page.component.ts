import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProjectVO} from "../../model/project-v-o";
import {SectionVO} from "../../model/section-v-o";

@Component({
  selector: 'project-page',
  templateUrl: './project-page.component.html',
  styleUrls: ['./project-page.component.css']
})
export class ProjectPageComponent implements OnInit {

  projectVO!: ProjectVO;
  currentSection!: SectionVO;

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.data.forEach(data => {
      this.projectVO = data['projectVO'];
      this.currentSection = this.projectVO.chapters[0].sections[0];
    });
  }

}
