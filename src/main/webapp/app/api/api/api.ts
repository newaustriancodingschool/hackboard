export * from './project.service';
import { ProjectService } from './project.service';
export * from './projectRole.service';
import { ProjectRoleService } from './projectRole.service';
export * from './tag.service';
import { TagService } from './tag.service';
export const APIS = [ProjectService, ProjectRoleService, TagService];
