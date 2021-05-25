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

  public dataLoaded: boolean = false;
  public cvVo: CvVo | undefined;

  constructor(private appStateService: AppStateService,
              private route: ActivatedRoute,
              private router: Router,
              private cvService: CvService,
              private cvMapper: CvMapperService,
              private userService: UsersService) {

    let usernameFromUrl = route.snapshot.params['username'];
    if (usernameFromUrl !== this.appStateService.getSelectedUsername()) {
      userService.userExists(usernameFromUrl).subscribe(
        user => {
          this.appStateService.setSelectedUser(user);
          this.getUsersCv();
          this.dataLoaded = true;
        },
        _ => router.navigate(['/users']));
    }else {
      this.getUsersCv();
      this.dataLoaded = true;
    }
  }

  ngOnInit = (): void => {};

  private getUsersCv() {
    this.cvService.getCvByUsername(this.appStateService.getSelectedUsername())
      .subscribe(cvTO => {
        this.cvVo = this.cvMapper.mapToVO(cvTO)
      })
  }
}
