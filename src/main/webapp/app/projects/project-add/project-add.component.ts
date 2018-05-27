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
  data: ProjectDto = { title: '', description: '', ownerId: 0, github: '', projectRole: [] };
  roleData: ProjectRoleDto = { roleName: '', color: '', count: 0 };
  roles: Array<ProjectRoleDto>;
  projectRoles: Array<ProjectRoleDto>;

  constructor(
    private projectService: ProjectService,
    private router: Router,
    private projectRoleService: ProjectRoleService
  ) {}

  ngOnInit() {
    this.projectRoleService.listProjectRoles().subscribe(roles => (this.roles = roles));
    this.projectRoles = [];
  }

  submit() {
    this.data.projectRole = this.projectRoles;
    this.projectService.addProject(this.data).subscribe(() => this.router.navigate(['/projects']));
  }

  addRole(roleValue, countValue) {
    const newRoleData = { roleName: '', color: '', count: 0 };
    newRoleData.roleName = roleValue;
    newRoleData.count = countValue;
    this.projectRoles.push(newRoleData);
  }
}
