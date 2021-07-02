import {Component, OnInit} from '@angular/core';
import {ResumeService} from "../../service/resume.service";
import {ResumeVO} from "../../model/resume-v-o";
import {ActivatedRoute} from "@angular/router";
import {ResumeMapperService} from "../../mapping/resume-mapper.service";
import {AuthService} from "../../../service/auth.service";
import {ToastrService} from "../../../shared/service/toastr.service";

@Component({
  selector: 'resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.css']
})
export class ResumeComponent implements OnInit {
  resumeVO!: ResumeVO;

  constructor(private resumeService: ResumeService,
              public activatedRoute: ActivatedRoute,
              private resumeMapperService: ResumeMapperService,
              public authService: AuthService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.resumeService.getResumeByEmail(this.activatedRoute.snapshot.params['email'])
      .subscribe(resumeTO => {
        this.resumeVO = this.resumeMapperService.mapToVO(resumeTO);
        this.resumeVO.experiences.sort((a, b) => b.startDate.localeCompare(a.startDate));
      });
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
}
