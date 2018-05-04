import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HackboardSharedModule } from '../shared';

import {
  Register,
  ActivateService,
  PasswordService,
  PasswordResetInitService,
  PasswordResetFinishService,
  SessionsService,
  SessionsComponent,
  PasswordStrengthBarComponent,
  RegisterComponent,
  ActivateComponent,
  PasswordComponent,
  PasswordResetInitComponent,
  PasswordResetFinishComponent,
  SettingsComponent,
  accountState
} from './';
import { ProjectViewComponent } from './project-view/project-view.component';
import { ProjectViewModule } from './project-view/project-view.module';
import { ProjectEditComponent } from './project-edit/project-edit.component';
import { ProjectEditModule } from './project-edit/project-edit.module';
import { ProjectsComponent } from './projects/projects.component';
import { ProjectsModule } from './projects/projects.module';

@NgModule({
  imports: [
    HackboardSharedModule,
    RouterModule.forChild(accountState),
    ProjectViewModule,
    ProjectEditModule,
    ProjectsModule
  ],
  declarations: [
    ActivateComponent,
    RegisterComponent,
    PasswordComponent,
    PasswordStrengthBarComponent,
    PasswordResetInitComponent,
    PasswordResetFinishComponent,
    SessionsComponent,
    SettingsComponent,
    ProjectViewComponent,
    ProjectEditComponent,
    ProjectsComponent
  ],
  providers: [
    SessionsService,
    Register,
    ActivateService,
    PasswordService,
    PasswordResetInitService,
    PasswordResetFinishService
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HackboardAccountModule {}
