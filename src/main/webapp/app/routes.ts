import {Route} from '@angular/router';
import {ProjectListComponent} from './project-list/project-list.component';

export const routes: Array<Route> = [
    {path: 'projects/list', component: ProjectListComponent}
];
