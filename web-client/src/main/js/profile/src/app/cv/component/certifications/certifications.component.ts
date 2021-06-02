import {Component, Input, OnInit} from '@angular/core';
import {CertificationVO} from "../../model/certification-v-o";

@Component({
  selector: 'certifications',
  templateUrl: './certifications.component.html',
  styleUrls: ['./certifications.component.css']
})
export class CertificationsComponent implements OnInit {

  @Input()
  certifications!: CertificationVO[];

  constructor() { }

  ngOnInit(): void {
  }

}
