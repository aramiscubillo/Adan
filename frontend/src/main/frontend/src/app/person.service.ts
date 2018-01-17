import { Injectable } from '@angular/core';
import { Person } from './pojos/person';
import { Response } from './pojos/response';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class PersonService {

  private personUrl = '/api/person';

  constructor(private http: HttpClient) { }

  private log(message: string) {
    console.log('log: '+message);
  }
  private handleError<T> (operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

/*  getPerson(id: number): Observable<Person> {
    return of(PERSONS.find(person => person.id === id));
  }*/
  /** GET hero by id. Will 404 if id not found */
  getPerson(id: number): Observable<Person> {
    const url = `${this.personUrl}/${id}`;
    return this.http.get<Person>(url).pipe(
      tap(_ => this.log(`fetched person id=${id}`)),
      catchError(this.handleError<Person>(`getPerson id=${id}`))
    );
  }

  getPersons(): Observable<Person[]> {
    return this.http.get<Person[]>(this.personUrl)
    .pipe(
      tap(persons => this.log(`fetched persons: `+persons)),
      catchError(this.handleError('getHeroes',[]))
    );
  }


  updatePerson (person: Person): Observable<any> {
    return this.http.put(this.personUrl, person, httpOptions).pipe(
      tap(_ => this.log(`updated person id=${person.id}`)),
      catchError(this.handleError<any>('updateHero'))
    );
  }


  /** POST: add a new hero to the server */
  addPerson (person: Person): Observable<Person> {
    return this.http.post<Person>(this.personUrl, person, httpOptions).pipe(
      tap((_: Person) => this.log(`added person w/ id=${person.id}`)),
      catchError(this.handleError<Person>('addPerson'))
    );
  }

}
