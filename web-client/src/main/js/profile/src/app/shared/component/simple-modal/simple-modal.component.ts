import {Component, Input, OnInit} from '@angular/core';
import {Size} from "../../model/enum/size";

@Component({
  selector: 'simple-modal',
  templateUrl: './simple-modal.component.html',
  styleUrls: ['./simple-modal.component.css']
})
export class SimpleModalComponent implements OnInit {
  @Input()
  title!: string;

  @Input()
  modalId!: string;

  @Input() size: Size = Size.MEDIUM;

  constructor() {
  }

  ngOnInit(): void {
  }

  public getModalDialogClass(): string {
    if (this.size === Size.SMALL) {
      return 'modal-dialog' + ' modal-sm';
    } else if (this.size === Size.LARGE) {
      return 'modal-dialog' + ' modal-lg';
    } else if (this.size === Size.EXTRA_LARGE) {
      return 'modal-dialog' + ' modal-xl';
    } else {
      return 'modal-dialog';
    }
  }
}


