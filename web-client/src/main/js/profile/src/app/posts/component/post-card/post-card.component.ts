import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PostVO} from "../../model/post-v-o";

@Component({
  selector: 'post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent implements OnInit {

  @Input() postVO!: PostVO;
  @Input() settingAvailable: boolean = false;

  @Output() onDelete: EventEmitter<any> = new EventEmitter<any>();
  @Output() onSave: EventEmitter<PostVO> = new EventEmitter<PostVO>();
  editMode: boolean = false;
  postVOToEdit!: PostVO;

  constructor() {
  }

  ngOnInit(): void {
    this.postVOToEdit = {...this.postVO};
  }

  public editPost(): void {
    this.editMode = !this.editMode
  }

  public deletePost(): void {
    this.onDelete.emit();
  }

  public reset(): void {
    this.editMode = false;
    this.postVOToEdit = {...this.postVO};
  }

  public save(): void {
    this.editMode = false;
    this.onSave.emit(this.postVOToEdit);
  }
}
