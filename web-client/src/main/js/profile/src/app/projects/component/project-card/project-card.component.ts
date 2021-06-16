import {Component, Input, OnInit} from '@angular/core';
import {ProjectVO} from "../../model/project-v-o";
import {Router} from "@angular/router";

@Component({
  selector: 'project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.css']
})
export class ProjectCardComponent implements OnInit {

  @Input()
  projectVO!: ProjectVO;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public openProject(): void {
    this.router.navigate([this.router.url, this.projectVO.name]);
  }
}
