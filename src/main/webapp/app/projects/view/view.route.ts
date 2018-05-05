import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ViewComponent } from './view.component';

export const viewRoute: Route = {
  path: 'project-view',
  component: ViewComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'project-view.title'
  },
  canActivate: [UserRouteAccessService]
};
