import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SectionVO} from "../../model/section-v-o";

@Component({
  selector: 'navigation-element',
  templateUrl: './navigation-element.component.html',
  styleUrls: ['./navigation-element.component.css']
})
export class NavigationElementComponent implements OnInit {

  @Input() sectionVO!: SectionVO;
  @Output() onClick: EventEmitter<SectionVO> = new EventEmitter<SectionVO>()
  mouseover: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  getClassForNavigationElement(): string {
    return this.mouseover ? 'cursor-pointer bg-secondary' : 'cursor-pointer';
  }

  public elementClicked(sectionVO: SectionVO): void {
    this.onClick.emit(sectionVO);
  }
}
