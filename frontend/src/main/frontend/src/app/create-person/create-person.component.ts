import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Person } from '../pojos/person';
import { PersonService }  from '../person.service';

@Component({
  selector: 'app-create-person',
  templateUrl: './create-person.component.html',
  styleUrls: ['./create-person.component.css']
})
export class CreatePersonComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private personService: PersonService,
    private location: Location
  ) { }

  ngOnInit() {
  }

  person: Person = {
    id:0,
    name: '',
    lastName:'',
    petsOwn:0
  };



  save(): void {
    this.personService.addPerson(this.person)
       .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }

}
