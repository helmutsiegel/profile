import {Component, OnInit, ViewChild} from '@angular/core';
import {CvVO} from "../../model/cv-v-o";
import {ActivatedRoute, Router} from "@angular/router";
import {CvService} from "../../service/cv.service";
import {AuthService} from "../../../service/auth.service";
import {ExperienceVO} from "../../model/experience-v-o";
import {CvMapperService} from "../../mapping/cv-mapper.service";
import {LanguageVO} from "../../model/language-v-o";
import {CertificationVO} from "../../model/certification-v-o";
import {CertificationsComponent} from "../certifications/certifications.component";

@Component({
  selector: 'cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {

  public cvVO!: CvVO;
  @ViewChild('certifications') certificationsComponent!: CertificationsComponent;

  constructor(public activatedRoute: ActivatedRoute,
              private router: Router,
              private cvService: CvService,
              public authService: AuthService,
              private cvMapper: CvMapperService) {
  }

  ngOnInit(): void {

    this.activatedRoute.data.forEach(data => {
      this.cvVO = data['cvVO'];
      this.cvVO.experiences.sort((a, b) => {
        return b.startDate.localeCompare(a.startDate);
      })
    });
  };

  public saveAbout(event: string) {
    this.cvService.getByEmail(this.cvVO.personalInfoVO.email)
      .subscribe(cvTO => {
        cvTO.about = event;
        this.cvService.update(cvTO);
      })
  }

  public saveExperiences(experienceVOS: ExperienceVO[]): void {
    this.cvService.updateExperiences(experienceVOS.map(experienceVO => this.cvMapper.mapExperienceVOtoTO(experienceVO)))
      .subscribe(experienceTOs => {
        this.cvVO.experiences = this.cvMapper.mapExperiences(experienceTOs);
      });
  }

  public saveLanguages(languageVOS: LanguageVO[]): void {
    this.cvService.updateLanguages(languageVOS.map(languageVO => this.cvMapper.mapLanguageVOtoTO(languageVO)))
      .subscribe(languageTOs => {
        this.cvVO.languages = this.cvMapper.mapLanguages(languageTOs);
      });
  }

  public saveCertifications(certificationVOS: CertificationVO[]): void {
    this.certificationsComponent.saveModeOn();
    this.cvService.updateCertifications(certificationVOS.map(certificationVO => this.cvMapper.mapCertificationVOtoTO(certificationVO)))
      .subscribe(certificationTOs => {
        this.cvVO.certifications = this.cvMapper.mapCertifications(certificationTOs);
        this.certificationsComponent.saveModeOff();
      });
  }
}
