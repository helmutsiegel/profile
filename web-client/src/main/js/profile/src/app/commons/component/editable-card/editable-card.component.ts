import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'editable-card',
  templateUrl: './editable-card.component.html',
  styleUrls: ['./editable-card.component.css']
})
export class EditableCardComponent implements OnInit {

  @Input()
  title!: string;

  @Input()
  text!: string;
  editMode: boolean = false;

  @Input()
  editable: boolean = false;

  @Output()
  public onSave: EventEmitter<string> = new EventEmitter<string>();

  constructor() {
  }

  ngOnInit(): void {
  }

  public edit(): void {
    this.editMode = !this.editMode;
  }

  public save(): void {
    this.onSave.emit(this.text);
    this.editMode = !this.editMode;
  }
}
