import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CertificationVO} from "../../model/certification-v-o";

@Component({
  selector: 'certifications',
  templateUrl: './certifications.component.html',
  styleUrls: ['./certifications.component.css']
})
export class CertificationsComponent implements OnInit {
  @Input() certifications!: CertificationVO[];
  @Input() editable!: boolean;
  @Output() onSave: EventEmitter<CertificationVO[]> = new EventEmitter<CertificationVO[]>();
  editMode: boolean = false;
  certificationsToEdit: CertificationVO[] = [];
  saveMode: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  public editCertifications(): void {
    this.certificationsToEdit = [];
    this.certifications.forEach(val => this.certificationsToEdit.push(Object.assign({}, val)));
    this.editMode = !this.editMode;
  }

  public addCertification() {
    this.certificationsToEdit.push(new CertificationVO(null, '', '', '', null));
  }

  public save() {
    this.editMode = false;
    this.onSave.emit(this.certificationsToEdit);
  }

  public reset() {
    this.editMode = false;
    this.certificationsToEdit = [...this.certifications];
  }

  public saveModeOn(): void {
    this.saveMode = true;
  }

  public saveModeOff(): void {
    this.saveMode = false;
  }
}
