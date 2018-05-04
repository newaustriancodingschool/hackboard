import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ProjectViewComponent } from './project-view.component';

export const projectViewRoute: Route = {
  path: 'project-view',
  component: ProjectViewComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'project-view.title'
  },
  canActivate: [UserRouteAccessService]
};
