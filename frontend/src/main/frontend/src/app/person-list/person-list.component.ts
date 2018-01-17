import { Component, OnInit } from '@angular/core';
import { Person } from '../pojos/person';
import { PersonService } from '../person.service';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  constructor(private personService: PersonService) { }

  persons : Person[];

  ngOnInit() {
    this.getPersons();
  }


  getPersons(): void {
    this.personService.getPersons()
     .subscribe(persons => this.persons = persons);
  }



}
