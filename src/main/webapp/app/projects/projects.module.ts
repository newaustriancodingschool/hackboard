import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HackboardSharedModule } from '../shared';

import {
  projectsState,
  AddComponent,
  AddModule,
  EditComponent,
  EditModule,
  ListComponent,
  ListModule,
  ViewComponent,
  ViewModule
} from './';

@NgModule({
  imports: [
    HackboardSharedModule,
    RouterModule.forChild(projectsState),
    AddModule,
    EditModule,
    ListModule,
    ViewModule
  ],
  declarations: [AddComponent, EditComponent, ListComponent, ViewComponent],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HackboardProjectsModule {}
