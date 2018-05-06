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

@NgModule({
  imports: [HackboardSharedModule, RouterModule.forChild(projectsState)],
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
