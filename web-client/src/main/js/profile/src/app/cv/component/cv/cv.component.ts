import {Component, OnInit} from '@angular/core';
import {CvVo} from "../../model/cv-vo";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  public cvVo!: CvVo;

  constructor(private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.cvVo = this.route.snapshot.data['cvVO']
  };

}
