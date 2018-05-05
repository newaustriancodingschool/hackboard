import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { AddComponent } from './add.component';

export const addRoute: Route = {
  path: 'project-add',
  component: AddComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'project-add.title'
  },
  canActivate: [UserRouteAccessService]
};
