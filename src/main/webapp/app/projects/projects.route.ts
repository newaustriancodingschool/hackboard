import { Routes } from '@angular/router';

import { addRoute, editRoute, listRoute, viewRoute } from './';

const PROJECTS_ROUTES = [addRoute, editRoute, listRoute, viewRoute];

export const projectsState: Routes = [
  {
    path: '',
    children: PROJECTS_ROUTES
  }
];
