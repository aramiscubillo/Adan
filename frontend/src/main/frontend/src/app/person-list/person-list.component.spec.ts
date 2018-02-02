import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonListComponent } from './person-list.component';
import { RouterTestingModule} from "@angular/router/testing";
import { FormsModule } from '@angular/forms';
import { PersonService }  from '../person.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


describe('PersonListComponent', () => {
  let component: PersonListComponent;

  let fixture: ComponentFixture<PersonListComponent>;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonListComponent],
      imports:[RouterTestingModule, FormsModule, HttpClientTestingModule],
      providers:[PersonService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
