import {Component, Input, OnInit} from '@angular/core';
import {PersonalInfoVO} from "../../model/vo/personal-info-v-o";
import {Router} from "@angular/router";

@Component({
  selector: 'personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {
  @Input() editable!: boolean;
  @Input() personalInfoVO!: PersonalInfoVO;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public edit(): void {
    this.router.navigate(['user/profile']);
  }
}
