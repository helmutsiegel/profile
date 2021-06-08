import {Component, OnInit} from '@angular/core';
import {CvVO} from "../../model/cv-v-o";
import {ActivatedRoute} from "@angular/router";
import {CvService} from "../../service/cv.service";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  public cvVO!: CvVO;

  constructor(private route: ActivatedRoute,
              private cvService: CvService) {}

  ngOnInit(): void {
    this.route.params.forEach(_ => {
      this.cvVO = this.route.snapshot.data['cvVO'];
    });
  };

  public saveAbout(event: string) {
    this.cvService.getCvByUsername(this.cvVO.personalInfoVO.username)
      .subscribe(cvTO => {
        cvTO.about = event;
        this.cvService.updateCV(cvTO);
      })
  }
}
