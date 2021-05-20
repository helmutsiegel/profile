import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersNavbarComponent } from './users-navbar.component';

describe('UsersSidebarComponent', () => {
  let component: UsersNavbarComponent;
  let fixture: ComponentFixture<UsersNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersNavbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
