import {Component, OnInit} from '@angular/core';
import {CvVO} from "../../model/cv-v-o";
import {ActivatedRoute, Router} from "@angular/router";
import {CvService} from "../../service/cv.service";
import {AuthService} from "../../../service/auth.service";
import {ExperienceVO} from "../../model/experience-v-o";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  public cvVO!: CvVO;

  constructor(public activatedRoute: ActivatedRoute,
              private router: Router,
              private cvService: CvService,
              public authService: AuthService) {
  }

  ngOnInit(): void {

    this.activatedRoute.data.forEach(data => {
      this.cvVO = data['cvVO'];
      this.cvVO.experiences.sort((a, b) => {
        return b.startDate.localeCompare(a.startDate);
      })
    });
  };

  public saveAbout(event: string) {
    this.cvService.getByEmail(this.cvVO.personalInfoVO.email)
      .subscribe(cvTO => {
        cvTO.about = event;
        this.cvService.update(cvTO);
      })
  }
}
