import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelmutBusinessCardComponent } from './helmut-business-card.component';

describe('HelmutBusinessCardComponent', () => {
  let component: HelmutBusinessCardComponent;
  let fixture: ComponentFixture<HelmutBusinessCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HelmutBusinessCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HelmutBusinessCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
