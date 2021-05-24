import {Component, Input, OnInit} from '@angular/core';
import {CvService} from "../../service/cv.service";
import {CvTo} from "../../model/cv-to";
import {AppStateService} from "../../../commons/service/app-state.service";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  @Input()
  public cvTo!: CvTo;

  constructor(private appStateService: AppStateService,
              private cvService: CvService) {
  }

  ngOnInit(): void {
    this.cvService.getCvByUsername(this.appStateService.getSelectedUsername());
  }
}
