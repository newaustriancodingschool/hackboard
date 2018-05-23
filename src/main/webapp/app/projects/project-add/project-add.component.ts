import { ProjectRoleService } from './../../api/api/projectRole.service';
import { ProjectDtoRoles } from './../../api/model/projectDtoRoles';
import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { Router } from '@angular/router';

@Component({
  templateUrl: './project-add.component.html',
  styles: []
})
export class ProjectAddComponent implements OnInit {
  data: ProjectDto = { title: '', description: '', ownerId: 0, github: '' };
  roleData: ProjectDtoRoles = { roleName: '', color: '' };
  roles: Array<ProjectDtoRoles>;

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
