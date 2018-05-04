import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ProjectsComponent } from './projects.component';

export const projectsRoute: Route = {
  path: 'projects',
  component: ProjectsComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'global/menu/account/projects'
  },
  canActivate: [UserRouteAccessService]
};
