import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPencilComponent } from './edit-pencil.component';

describe('EditPencilComponent', () => {
  let component: EditPencilComponent;
  let fixture: ComponentFixture<EditPencilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditPencilComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPencilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
