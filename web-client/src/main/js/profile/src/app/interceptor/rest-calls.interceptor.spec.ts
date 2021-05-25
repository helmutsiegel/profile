import { TestBed } from '@angular/core/testing';

import { RestCallsInterceptor } from './rest-calls.interceptor';

describe('RestCallsInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      RestCallsInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: RestCallsInterceptor = TestBed.inject(RestCallsInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
