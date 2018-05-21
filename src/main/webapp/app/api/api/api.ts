export * from './project.service';
import { ProjectService } from './project.service';
export * from './projectRole.service';
import { ProjectRoleService } from './projectRole.service';
export const APIS = [ProjectService, ProjectRoleService];
