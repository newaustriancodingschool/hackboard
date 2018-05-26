import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectRoleDto } from './../../api/model/projectRoleDto';
import { ProjectRoleService } from './../../api/api/projectRole.service';

@Component({
  templateUrl: './project-view.component.html'
})
export class ProjectViewComponent implements OnInit {
  data: ProjectDto = { id: 0, title: '', description: '', github: '' };
  roleData: ProjectRoleDto = { roleName: '', color: '' };
  roles: Array<any>;
  value: any;

  constructor(
    private projectRoleService: ProjectRoleService,
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.projectService.viewProject(id).subscribe(project => {
      this.data = project;
      this.projectRoleService.listProjectRoles().subscribe(roles => (this.roles = roles));
    });
    this.projectRoleService
      .listProjectRoles()
      .subscribe(projectRoles => (this.roles = projectRoles));
  }
  delete() {
    this.projectService
      .deleteProject(this.data.id)
      .subscribe(() => this.router.navigate(['/projects']));
  }

  selected(value: any): void {
    console.log('Selected value is: ', value);
  }

  removed(value: any): void {
    console.log('Removed value is: ', value);
  }

  refreshValue(value: any): void {
    this.value = value;
  }

  rolesToString(value: Array<any> = []): string {
    return value
      .map((item: any) => {
        return item.text;
      })
      .join(',');
  }
}
