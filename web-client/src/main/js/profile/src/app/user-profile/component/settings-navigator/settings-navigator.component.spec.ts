import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsNavigatorComponent } from './settings-navigator.component';

describe('SettingsNavigatorComponent', () => {
  let component: SettingsNavigatorComponent;
  let fixture: ComponentFixture<SettingsNavigatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SettingsNavigatorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsNavigatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
