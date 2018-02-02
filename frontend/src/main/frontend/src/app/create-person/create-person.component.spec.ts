import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CreatePersonComponent } from './create-person.component';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule} from "@angular/router/testing";
import { PersonService }  from '../person.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('CreatePersonComponent', () => {
  let component: CreatePersonComponent;
  let fixture: ComponentFixture<CreatePersonComponent>;
  let service: PersonService;

  var person={};

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePersonComponent ],
      imports:[FormsModule, RouterTestingModule, HttpClientTestingModule],
      providers:[PersonService],
    })
    .compileComponents();

    var person={
      id:0,
      name: '',
      lastName:'',
      petsOwn:0

    };

  }));

  beforeEach(() => {

    fixture = TestBed.createComponent(CreatePersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  afterEach(()=>{
    var person={};
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
