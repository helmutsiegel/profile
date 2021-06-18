import { TestBed } from '@angular/core/testing';

import { LoginPageRouteActivatorService } from './login-page-route-activator.service';

describe('LoginPageRouteActivatorService', () => {
  let service: LoginPageRouteActivatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginPageRouteActivatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
