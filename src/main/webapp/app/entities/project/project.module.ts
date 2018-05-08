import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HackboardSharedModule } from '../../shared';
import { HackboardAdminModule } from '../../admin/admin.module';
import {
  ProjectService,
  ProjectPopupService,
  ProjectComponent,
  ProjectDetailComponent,
  ProjectDialogComponent,
  ProjectPopupComponent,
  ProjectDeletePopupComponent,
  ProjectDeleteDialogComponent,
  projectRoute,
  projectPopupRoute
} from './';

const ENTITY_STATES = [...projectRoute, ...projectPopupRoute];

@NgModule({
  imports: [HackboardSharedModule, HackboardAdminModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProjectComponent,
    ProjectDetailComponent,
    ProjectDialogComponent,
    ProjectDeleteDialogComponent,
    ProjectPopupComponent,
    ProjectDeletePopupComponent
  ],
  entryComponents: [
    ProjectComponent,
    ProjectDialogComponent,
    ProjectPopupComponent,
    ProjectDeleteDialogComponent,
    ProjectDeletePopupComponent
  ],
  providers: [ProjectService, ProjectPopupService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HackboardProjectModule {}
