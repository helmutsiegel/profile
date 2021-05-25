import { TestBed } from '@angular/core/testing';

import { UsersPageRouteActivatorService } from './users-page-route-activator.service';

describe('UsersPageRouteActivatorService', () => {
  let service: UsersPageRouteActivatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsersPageRouteActivatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
