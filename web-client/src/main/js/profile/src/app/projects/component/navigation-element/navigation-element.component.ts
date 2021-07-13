import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SectionVO} from "../../model/section-v-o";

@Component({
  selector: 'navigation-element',
  templateUrl: './navigation-element.component.html',
  styleUrls: ['./navigation-element.component.css']
})
export class NavigationElementComponent implements OnInit {

  @Input() selected!: boolean;
  @Input() sectionVO!: SectionVO;
  @Output() onClick: EventEmitter<SectionVO> = new EventEmitter<SectionVO>()
  mouseover: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  getClassForNavigationElement(): string {
    let style = 'cursor-pointer'
    if (this.selected) {
      style += ' font-weight-bold'
    }
    if (this.mouseover) {
      style += ' bg-light'
    }
    return style;
  }

  public elementClicked(sectionVO: SectionVO): void {
    this.onClick.emit(sectionVO);
  }
}
