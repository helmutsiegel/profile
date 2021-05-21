import {Component, Input, OnInit} from '@angular/core';
import {UserVo} from "../../model/user-vo";

@Component({
  selector: 'user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {

  @Input() user!: UserVo;

  constructor() {
  }

  ngOnInit(): void {
  }

}
