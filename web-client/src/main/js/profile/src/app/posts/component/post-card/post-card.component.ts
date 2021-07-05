import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent implements OnInit {

  @Input() title!: string;
  @Input() content!: string;
  @Input() created!: string;
  @Input() settingAvailable: boolean = false;
  @Input() tags: string[] = ['vdgcd', 'dcdc'];

  @Output() onDelete: EventEmitter<any> = new EventEmitter<any>();
  @Output() onSave: EventEmitter<string> = new EventEmitter<string>();
  editMode: boolean = false;
  contentToEdit!: string;

  constructor() {
  }

  ngOnInit(): void {
    this.contentToEdit = this.content;
  }

  public editPost(): void {
    this.editMode = !this.editMode
  }

  public deletePost(): void {
    this.onDelete.emit();
  }

  public cancel(): void {
    this.editMode = false;
    this.contentToEdit = this.content;
  }

  public save(): void {
    this.editMode = false;
    this.onSave.emit(this.contentToEdit);
  }

  public reset(): void {
    this.contentToEdit = this.content;
  }
}
