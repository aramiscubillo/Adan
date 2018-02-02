import { TestBed, inject } from '@angular/core/testing';

import { PersonService } from './person.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('PersonService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PersonService],
      imports:[HttpClientTestingModule]
    });
  });

  it('should be created', inject([PersonService], (service: PersonService) => {
    expect(service).toBeTruthy();
  }));
});
