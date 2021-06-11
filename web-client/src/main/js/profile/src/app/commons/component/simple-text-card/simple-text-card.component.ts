import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'simple-test-card',
  templateUrl: './simple-text-card.component.html',
  styleUrls: ['./simple-text-card.component.css']
})
export class SimpleTextCardComponent implements OnInit {

  @Input() title!: string;
  @Input() text!: string;
  @Input() editable: boolean = false;
  @Input() length: number = -1;

  @Output() public onSave: EventEmitter<string> = new EventEmitter<string>();

  editMode: boolean = false;
  textToEdit!: string;

  constructor() {}

  ngOnInit(): void {
    this.textToEdit = this.text;
  }

  public edit(): void {
    this.editMode = !this.editMode;
  }

  public save(): void {
    this.text = this.textToEdit;
    this.onSave.emit(this.text);
    this.editMode = !this.editMode;
  }

  public cancelEdit(): void {
    this.textToEdit = this.text;
    this.editMode = !this.editMode;
  }
}
