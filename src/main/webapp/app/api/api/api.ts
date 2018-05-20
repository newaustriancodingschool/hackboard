export * from './competency.service';
import { CompetencyService } from './competency.service';
export * from './project.service';
import { ProjectService } from './project.service';
export const APIS = [CompetencyService, ProjectService];
