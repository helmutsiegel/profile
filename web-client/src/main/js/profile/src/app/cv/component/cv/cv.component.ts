import {Component, OnDestroy, OnInit} from '@angular/core';
import {CvVO} from "../../model/cv-v-o";
import {ActivatedRoute, Router} from "@angular/router";
import {CvService} from "../../service/cv.service";
import {AuthService} from "../../../service/auth.service";
import {UserTO} from "../../../shared/model/to/user-t-o";
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
              private router: Router,
              private cvService: CvService,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.subscription = this.authService.getCurrentUser().subscribe(userTO => {
      this.currentUser = userTO;
    });
    this.activatedRoute.data.forEach(data => {
      this.cvVO = data['cvVO'];
    });
  };

  public saveAbout(event: string) {
    this.cvService.getByEmail(this.cvVO.personalInfoVO.email)
      .subscribe(cvTO => {
        cvTO.about = event;
        this.cvService.update(cvTO);
      })
  }

  public loggedInUserIsOnTheyPage(): boolean {
    return this.currentUser && (this.currentUser.email === this.activatedRoute.snapshot.params['email']);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public editPersonalInfo(): void {
    this.router.navigate(['user/profile']);
  }
}
