import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonListComponent }      from './person-list/person-list.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { CreatePersonComponent }   from './create-person/create-person.component';
import { PersonDetailComponent }   from './person-detail/person-detail.component';


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'create', component: CreatePersonComponent },
  { path: 'persons', component: PersonListComponent },
  { path: 'detail/:id', component: PersonDetailComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
