import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'collapsible-card',
  templateUrl: './collapsible-card.component.html',
  styleUrls: ['./collapsible-card.component.css']
})
export class CollapsibleCardComponent implements OnInit {
  @Input()
  title!: string;

  @Input()
  collapsed: boolean = true;

  constructor() {
  }

  ngOnInit(): void {
  }

  switchCollapse() : void{
    this.collapsed = !this.collapsed;
  }
}
