import { Routes } from '@angular/router';

import { projectAddRoute, projectEditRoute, projectListRoute, projectViewRoute } from './';

const PROJECTS_ROUTES = [projectAddRoute, projectEditRoute, projectListRoute, projectViewRoute];

export const projectsState: Routes = [
  {
    path: '',
    children: PROJECTS_ROUTES
  }
];
