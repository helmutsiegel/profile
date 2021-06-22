import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'simple-text-card',
  templateUrl: './simple-text-card.component.html',
  styleUrls: ['./simple-text-card.component.css']
})
export class SimpleTextCardComponent implements OnInit {

  _text!: string;
  @Input() title!: string;
  @Input() editable: boolean = false;
  @Input() length: number = -1;

  @Output() public onSave: EventEmitter<string> = new EventEmitter<string>();

  editMode: boolean = false;
  textToEdit!: string;

  @Input()
  get text(): string {
    return this._text;
  }
  set text(text: string) {
    this._text = text;
    this.textToEdit = text;
  }

  constructor() {
  }

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

  public isEdited(): boolean {
    return this.textToEdit !== this._text;
  }
}
