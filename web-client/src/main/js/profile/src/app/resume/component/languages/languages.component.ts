import {Component, Input, OnInit} from '@angular/core';
import {LanguageVO} from "../../model/language-v-o";

@Component({
  selector: 'languages',
  templateUrl: './languages.component.html',
  styleUrls: ['./languages.component.css']
})
export class LanguagesComponent implements OnInit {

  @Input()
  languages!: LanguageVO[];

  constructor() {
  }

  ngOnInit(): void {
  }

}
