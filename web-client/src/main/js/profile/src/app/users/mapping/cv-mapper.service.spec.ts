import { TestBed } from '@angular/core/testing';

import { CvMapperService } from './cv-mapper.service';

describe('CvMapperService', () => {
  let service: CvMapperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CvMapperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
