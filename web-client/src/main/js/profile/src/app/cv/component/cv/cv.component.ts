import {Component, OnDestroy, OnInit} from '@angular/core';
import {CvVO} from "../../model/cv-v-o";
import {ActivatedRoute, Router} from "@angular/router";
import {CvService} from "../../service/cv.service";
import {AuthService} from "../../../service/auth.service";
import {UserTO} from "../../../commons/model/to/user-t-o";
import {Subscription} from "rxjs";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit, OnDestroy {

  public cvVO!: CvVO;
  private currentUser!: UserTO;
  private subscription!: Subscription;

  constructor(private activatedRoute: ActivatedRoute,
              private cvService: CvService,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.subscription = this.authService.getCurrentUser().subscribe(userTO => {
      this.currentUser = userTO;
    });
    this.activatedRoute.params.forEach(_ => {
      this.cvVO = this.activatedRoute.snapshot.data['cvVO'];
    });
  };

  public saveAbout(event: string) {
    this.cvService.getCvByUsername(this.cvVO.personalInfoVO.username)
      .subscribe(cvTO => {
        cvTO.about = event;
        this.cvService.updateCV(cvTO);
      })
  }

  public cvIsFromCurrentUser(): boolean {
    return this.currentUser && (this.currentUser.username === this.activatedRoute.snapshot.params['username']);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
