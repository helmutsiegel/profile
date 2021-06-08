import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditableCardComponent } from './editable-card.component';

describe('EditableCardComponent', () => {
  let component: EditableCardComponent;
  let fixture: ComponentFixture<EditableCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditableCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditableCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
