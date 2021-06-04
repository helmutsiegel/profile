import { TestBed } from '@angular/core/testing';

import { ResumeMapperService } from './resume-mapper.service';

describe('ResumeMapperService', () => {
  let service: ResumeMapperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResumeMapperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
