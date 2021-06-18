import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PersonalInfoVO} from "../../model/vo/personal-info-v-o";

@Component({
  selector: 'personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {
  private pencilClass = 'bi-pencil';

  @Input() editable!: boolean;
  @Input() personalInfoVO!: PersonalInfoVO;
  @Output() onEdit: EventEmitter<any> = new EventEmitter<any>();

  constructor() {
  }

  ngOnInit(): void {
  }

  public edit(): void {
    this.onEdit.emit();
  }

  public getClassForPencil(): string {
    return 'bi ' + this.pencilClass + ' float-right'
  }

  public mouseoverPencil(): void {
    this.pencilClass = 'bi-pencil-fill'
  }

  public mouseleavePencil(): void {
    this.pencilClass = 'bi-pencil'
  }
}
