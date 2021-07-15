import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ExperienceVO} from "../../model/experience-v-o";

@Component({
  selector: 'experiences',
  templateUrl: './experiences.component.html',
  styleUrls: ['./experiences.component.css']
})
export class ExperiencesComponent implements OnInit {

  @Input() experiences!: ExperienceVO[];
  @Input() editable!: boolean;
  editMode: boolean = false;
  experiencesToEdit: ExperienceVO[] = [];

  @Output() onSave: EventEmitter<ExperienceVO[]> = new EventEmitter<ExperienceVO[]>();

  constructor() {
  }

  ngOnInit(): void {
    this.experiences.forEach(val => this.experiencesToEdit.push(Object.assign({}, val)));
  }

  public editExperiences(): void {
    this.editMode = !this.editMode;
  }

  public save(): void {
    this.editMode = false;
    this.onSave.emit(this.experiencesToEdit);
  }

  public reset(): void {
    this.editMode = false;
    this.experiencesToEdit = [...this.experiences];
  }

  public addExperience(): void {
    this.experiencesToEdit.push(new ExperienceVO('', '', ''));
  }
}
