import { Routes } from '@angular/router';

import { projectAddRoute, projectEditRoute, projectListRoute, projectViewRoute } from './';
import { userDetailsRoute } from './user-details/user-detail.route';

const PROJECTS_ROUTES = [
  projectAddRoute,
  projectEditRoute,
  projectListRoute,
  projectViewRoute,
  userDetailsRoute
];

export const projectsState: Routes = [
  {
    path: '',
    children: PROJECTS_ROUTES
  }
];
