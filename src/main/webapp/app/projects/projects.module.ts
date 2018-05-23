import { SelectModule } from 'ng2-select';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HackboardSharedModule } from '../shared';

import {
  projectsState,
  ProjectAddComponent,
  ProjectEditComponent,
  ProjectListComponent,
  ProjectViewComponent
} from './';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [FormsModule, HackboardSharedModule, RouterModule.forChild(projectsState), SelectModule],
  declarations: [
    ProjectAddComponent,
    ProjectEditComponent,
    ProjectListComponent,
    ProjectViewComponent
  ],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HackboardProjectsModule {}
