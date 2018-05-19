import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ProjectEditComponent } from './project-edit.component';

export const projectEditRoute: Route = {
  path: 'project-edit/:id',
  component: ProjectEditComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'project-edit.title'
  },
  canActivate: [UserRouteAccessService]
};
