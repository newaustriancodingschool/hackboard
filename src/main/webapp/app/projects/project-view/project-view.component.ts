import { ApplicationDto } from './../../api/model/applicationDto';
import { ProjectRoleService } from './../../api/api/projectRole.service';
import { ApplicationService } from './../../api/api/application.service';
import { ProjectRoleDto } from './../../api/model/projectRoleDto';
import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Principal } from './../../shared';
@Component({
  templateUrl: './project-view.component.html'
})
export class ProjectViewComponent implements OnInit {
  project: ProjectDto = { id: 0, title: '', description: '', projectRole: [] };
  applicant: ApplicationDto = { applicant: 0, projectId: 0, roleId: 0 };
  roleData: ProjectRoleDto = { id: 0, roleName: '', color: '', count: 0 };
  roles: Array<ProjectRoleDto>;
  isOwner: Boolean = false;
  settingsAccount: any;
  modalRef: NgbModalRef;
  isApplied: Boolean = false;
  applyButton: String = 'Apply';

  constructor(
    private projectService: ProjectService,
    private principal: Principal,
    private applicationService: ApplicationService,
    private projectRoleService: ProjectRoleService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.projectService.viewProject(id).subscribe(project => (this.project = project));
    this.projectRoleService.listProjectRoles().subscribe(roles => (this.roles = roles));

    this.principal.identity().then(account => {
      this.settingsAccount = this.copyAccount(account);
    });
  }

  getFilledArray(count) {
    return Array(count).fill(true);
  }

  toggleApply(roleid) {
    if (this.isApplied) {
      console.log(roleid);
      console.log('111111111111111111111');
      this.applicant.projectId = this.project.id;
      this.applicant.roleId = roleid;
      this.applicationService
        .addapplication(this.applicant)
        .subscribe(() => this.router.navigate(['/projects']));
      this.isApplied = !this.isApplied;
    } else {
      console.log(roleid);
      console.log('22222222222222222');
      this.applicant.projectId = this.project.id;
      this.applicant.roleId = roleid;
      this.applicationService
        .delapplication(this.applicant)
        .subscribe(() => this.router.navigate(['/projects']));
      this.isApplied = !this.isApplied;
    }
    this.isApplied ? (this.applyButton = 'Applied') : (this.applyButton = 'Apply');
  }

  delete() {
    this.projectService
      .deleteProject(this.project.id)
      .subscribe(() => this.router.navigate(['/projects']));
  }

  // apply(roleid) {
  //   this.applicant.projectId = this.project.id;
  //   this.applicant.roleId = roleid;

  //   this.applicationService
  //     .addapplication(this.applicant)
  //     .subscribe(() => this.router.navigate(['/projects']));
  // }
  // unApply(roleid) {
  //   this.applicant.projectId = this.project.id;
  //   this.applicant.roleId = roleid;

  //   this.applicationService
  //     .delapplication(this.applicant)
  //     .subscribe(() => this.router.navigate(['/projects']));
  // }

  copyAccount(account) {
    return {
      activated: account.activated,
      email: account.email,
      github: account.github,
      firstName: account.firstName,
      langKey: account.langKey,
      lastName: account.lastName,
      description: account.description,
      login: account.login,
      imageUrl: account.imageUrl
    };
  }
}
