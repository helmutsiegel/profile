import {Component, OnInit} from '@angular/core';
import {CvVO} from "../../model/cv-v-o";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  public cvVO!: CvVO;

  constructor(private route: ActivatedRoute,
              private router: Router) {

  }

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.cvVO = this.route.snapshot.data['cvVO'];
      }
    })
    this.cvVO = this.route.snapshot.data['cvVO'];
  };
}
