import {Component, OnInit} from '@angular/core';
import {ResumeService} from "../../service/resume.service";
import {ResumeVO} from "../../model/resume-v-o";
import {ActivatedRoute} from "@angular/router";
import {ResumeMapperService} from "../../mapping/resume-mapper.service";

@Component({
  selector: 'resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.css']
})
export class ResumeComponent implements OnInit {

  resumeVO!: ResumeVO;

  constructor(private resumeService: ResumeService,
              private activatedRoute: ActivatedRoute,
              private resumeMapperService: ResumeMapperService) {
  }

  ngOnInit(): void {
    this.resumeService.getResumeByUsername(this.activatedRoute.snapshot.params['username'])
      .subscribe(resumeTO => this.resumeVO = this.resumeMapperService.mapToVO(resumeTO));
  }
}
