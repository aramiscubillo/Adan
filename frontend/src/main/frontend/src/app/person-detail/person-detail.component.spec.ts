import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonDetailComponent } from './person-detail.component';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule} from "@angular/router/testing";
import { PersonService }  from '../person.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


describe('PersonDetailComponent', () => {
  let component: PersonDetailComponent;
  let fixture: ComponentFixture<PersonDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonDetailComponent ],
      imports:[FormsModule, RouterTestingModule, HttpClientTestingModule],
      providers:[PersonService],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
