import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CreateChapterTO} from "../../../shared/model/to/create-chapter-t-o";


@Component({
  selector: 'create-chapter',
  templateUrl: './create-chapter.component.html',
  styleUrls: ['./create-chapter.component.css']
})
export class CreateChapterComponent implements OnInit {

  @Output()
  private onCreate: EventEmitter<CreateChapterTO> = new EventEmitter<CreateChapterTO>();

  createChapterTO: CreateChapterTO = {
    title: ''
  };

  constructor() {
  }

  ngOnInit(): void {
  }

  public createChapter(): void {
    this.onCreate.emit(this.createChapterTO);
  }
}
