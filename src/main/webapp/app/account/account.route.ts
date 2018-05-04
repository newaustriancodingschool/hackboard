import { Routes } from '@angular/router';

import {
  activateRoute,
  passwordRoute,
  passwordResetFinishRoute,
  passwordResetInitRoute,
  registerRoute,
  sessionsRoute,
  settingsRoute,
  projectsRoute
} from './';

import { projectViewRoute } from './project-view/project-view.route';
import { projectEditRoute } from './project-edit/project-edit.route';

const ACCOUNT_ROUTES = [
  activateRoute,
  passwordRoute,
  passwordResetFinishRoute,
  passwordResetInitRoute,
  registerRoute,
  sessionsRoute,
  settingsRoute,
  projectViewRoute,
  projectEditRoute,
  projectsRoute
];

export const accountState: Routes = [
  {
    path: '',
    children: ACCOUNT_ROUTES
  }
];
