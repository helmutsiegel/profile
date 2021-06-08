import {Component, OnInit} from '@angular/core';
import {CvVO} from "../../model/cv-v-o";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  public cvVO!: CvVO;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.forEach(_ => {
      this.cvVO = this.route.snapshot.data['cvVO'];
    });
  };

  public saveAbout(event: string) {
    
  }
}
