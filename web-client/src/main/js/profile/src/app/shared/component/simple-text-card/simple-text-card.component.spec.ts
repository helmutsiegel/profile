import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleTextCardComponent } from './simple-text-card.component';

describe('SimpleTextCardComponent', () => {
  let component: SimpleTextCardComponent;
  let fixture: ComponentFixture<SimpleTextCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimpleTextCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SimpleTextCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
