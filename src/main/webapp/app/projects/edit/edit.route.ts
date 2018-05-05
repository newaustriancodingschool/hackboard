import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { EditComponent } from './edit.component';

export const editRoute: Route = {
  path: 'project-edit',
  component: EditComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'project-edit.title'
  },
  canActivate: [UserRouteAccessService]
};
