import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ProjectAddComponent } from './project-add.component';

export const projectAddRoute: Route = {
  path: 'project-add',
  component: ProjectAddComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'project-add.title'
  },
  canActivate: [UserRouteAccessService]
};
