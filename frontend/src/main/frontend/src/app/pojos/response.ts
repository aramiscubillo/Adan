import { Person } from './person';

export class Response {
  code: number;
  codeMessage: string;
  errorMessage: string;
  person: Person;
  persons: Person[];
}
