import {Component, OnInit} from '@angular/core';
import {CvService} from "../../service/cv.service";
import {AppStateService} from "../../../commons/service/app-state.service";
import {CvVo} from "../../model/cv-vo";
import {CvMapperService} from "../../../users/mapping/cv-mapper.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UsersService} from "../../../users/service/users.service";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  public cvVo: CvVo | undefined;

  constructor(private appStateService: AppStateService,
              private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.cvVo = this.route.snapshot.data['cvVO']
  };

}
