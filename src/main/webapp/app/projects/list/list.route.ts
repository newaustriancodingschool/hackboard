import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ListComponent } from './list.component';

export const listRoute: Route = {
  path: 'projects',
  component: ListComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'global/menu/account/projects'
  },
  canActivate: [UserRouteAccessService]
};
