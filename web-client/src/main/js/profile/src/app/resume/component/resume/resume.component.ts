import {Component, OnInit} from '@angular/core';
import {ResumeService} from "../../service/resume.service";
import {ResumeVO} from "../../model/resume-v-o";
import {ActivatedRoute} from "@angular/router";
import {ResumeMapperService} from "../../mapping/resume-mapper.service";
import {UserTO} from "../../../commons/model/to/user-t-o";
import {Subscription} from "rxjs";
import {AuthService} from "../../../service/auth.service";
import {ToastrService} from "../../../commons/service/toastr.service";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.css']
})
export class ResumeComponent implements OnInit {

  resumeVO!: ResumeVO;
  private currentUser!: UserTO;
  private subscription!: Subscription;

  constructor(private resumeService: ResumeService,
              private activatedRoute: ActivatedRoute,
              private resumeMapperService: ResumeMapperService,
              private authService: AuthService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.subscription = this.authService.getCurrentUser().subscribe(userTO => {
      this.currentUser = userTO;
    });
    this.resumeService.getResumeByEmail(this.activatedRoute.snapshot.params['email'])
      .subscribe(resumeTO => this.resumeVO = this.resumeMapperService.mapToVO(resumeTO));
  }

  public saveAbout(event: string) {
    this.resumeService.getResumeByEmail(this.resumeVO.personalInfoVO.email)
      .subscribe(resumeTO => {
        resumeTO.about = event;
        this.resumeService.update(resumeTO)
          .subscribe(_ => {
            this.toastr.success('Save successful')
          });
      })
  }

  public cvIsFromCurrentUser(): boolean {
    return this.currentUser && (this.currentUser.email === this.activatedRoute.snapshot.params['email']);
  }
}
