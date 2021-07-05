import { TestBed } from '@angular/core/testing';

import { PostMapperService } from './post-mapper.service';

describe('PostMapperService', () => {
  let service: PostMapperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PostMapperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
