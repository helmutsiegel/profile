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

  @Output() onDelete: EventEmitter<any> = new EventEmitter<any>();

  constructor() {
  }

  ngOnInit(): void {
  }

  public editPost(): void {

  }

  public deletePost(): void {
    this.onDelete.emit();
  }
}
