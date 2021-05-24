import {Component, OnInit} from '@angular/core';
import {CvService} from "../../service/cv.service";
import {AppStateService} from "../../../commons/service/app-state.service";
import {CvVo} from "../../model/cv-vo";
import {CvMapperService} from "../../../users/mapping/cv-mapper.service";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  public cvVo: CvVo | undefined;

  constructor(private appStateService: AppStateService,
              private cvService: CvService,
              private cvMapper: CvMapperService) {
  }

  ngOnInit(): void {
    this.cvService.getCvByUsername(this.appStateService.getSelectedUsername())
      .subscribe(cvTO => {
        this.cvVo = this.cvMapper.mapToVO(cvTO)
      })
  }
}
