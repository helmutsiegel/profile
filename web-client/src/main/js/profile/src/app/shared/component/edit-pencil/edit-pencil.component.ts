import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'edit-pencil',
  templateUrl: './edit-pencil.component.html',
  styleUrls: ['./edit-pencil.component.css']
})
export class EditPencilComponent implements OnInit {
  private pencilClass = 'bi-pencil';

  @Output() onClick: EventEmitter<any> = new EventEmitter<any>();

  constructor() {
  }

  ngOnInit(): void {
  }

  public getClassForPencil(): string {
    return 'bi ' + this.pencilClass;
  }

  public mouseoverPencil(): void {
    this.pencilClass = 'bi-pencil-fill'
  }

  public mouseleavePencil(): void {
    this.pencilClass = 'bi-pencil'
  }

  public edit(): void {
    this.onClick.emit();
  }
}
