import { ProjectRoleService } from './../../api/api/projectRole.service';
import { ProjectRoleDto } from './../../api/model/projectRoleDto';
import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { Router } from '@angular/router';

@Component({
  templateUrl: './project-add.component.html',
  styles: []
})
export class ProjectAddComponent implements OnInit {
  data: ProjectDto = { title: '', description: '', ownerId: 0, github: '' };
  roleData: ProjectRoleDto = { roleName: '', color: '' };
  roles: Array<ProjectRoleDto>;

  constructor(
    private projectService: ProjectService,
    private router: Router,
    private projectRoleService: ProjectRoleService
  ) {}

  ngOnInit() {
    this.projectRoleService.listProjectRoles().subscribe(roles => (this.roles = this.roles));
  }

  submit() {
    this.projectService.addProject(this.data).subscribe(() => this.router.navigate(['/projects']));
  }
}
