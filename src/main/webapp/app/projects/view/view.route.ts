import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ProjectViewComponent } from './view.component';

export const projectViewRoute: Route = {
  path: 'project-view/:id',
  component: ProjectViewComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'project-view.title'
  },
  canActivate: [UserRouteAccessService]
};
