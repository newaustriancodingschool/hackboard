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
  project: ProjectDto = {
    id: 0,
    title: '',
    description: '',
    github: '',
    projectRole: [],
    applicationDto: [],
    projectStories: []
  };
  applicant: ApplicationDto = { id: 0, applicant: 0, projectId: 0, roleId: 0, status: 1 };
  roleData: ProjectRoleDto = { id: 0, roleName: '', color: '', count: 0 };
  roles: Array<ProjectRoleDto>;
  isOwner: Boolean = false;
  settingsAccount: any;
  modalRef: NgbModalRef;
  isApplied: Boolean = false;
  applyButton: String[];
  rolesApply: number[];
  // captionBtn: String;
  id: number;
  isGithub: boolean;

  constructor(
    private projectService: ProjectService,
    private principal: Principal,
    private applicationService: ApplicationService,
    private projectRoleService: ProjectRoleService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.projectService.viewProject(this.id).subscribe(project => {
      this.project = project;
      this.project.github ? (this.isGithub = true) : (this.isGithub = false);
    });
    this.projectRoleService.listProjectRoles().subscribe(roles => (this.roles = roles));
    this.principal.identity().then(account => {
      this.settingsAccount = this.copyAccount(account);
    });
    this.applicationService.getRoleApplication(this.id).subscribe(rolesApply => {
      this.rolesApply = rolesApply;

      this.applyButton = new Array<string>(this.project.projectRole.length);
      this.applyButton = new Array(this.project.projectRole.length).fill('Apply');

      for (let i = 0; i < this.project.projectRole.length; i++) {
        for (let n = 0; n < this.rolesApply.length; n++) {
          if (this.project.projectRole[i].id === this.rolesApply[n]) {
            this.applyButton[i] = 'Applied';
          }
        }
      }
    });

    console.log(this.applyButton);
  }

  getFilledArray(count) {
    return Array(count).fill(true);
  }

  // checkisApply(roleId) {
  //   this.captionBtn = 'Apply';
  //   for (let i = 0; i < this.rolesApply.length; i++) {
  //     if (roleId === this.rolesApply[i]) {
  //       this.captionBtn = 'Applied';
  //     }
  //   }
  //   return this.captionBtn;
  // }

  toggleApply(roleId) {
    this.applicant.projectId = this.project.id;
    this.applicant.roleId = roleId;
    let roleFound = false;

    for (let i = 0; i < this.rolesApply.length; i++) {
      if (roleId === this.rolesApply[i]) {
        roleFound = true;
      }
    }
    if (roleFound === false) {
      this.applicationService
        .addapplication(this.applicant)
        .subscribe(() => this.router.navigate(['/#']));
      this.rolesApply.push(roleId);
    } else {
      this.applicationService
        .delapplication(this.project.id, roleId)
        .subscribe(() => this.router.navigate(['/#']));
      for (let index = 0; index < this.rolesApply.length; index++) {
        if (this.rolesApply[index] === roleId) {
          delete this.rolesApply[index];
        }
      }
    }
  }

  deleteProject() {
    this.projectService
      .deleteProject(this.project.id)
      .subscribe(() => this.router.navigate(['/projects']));
  }

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

  changeStatus(roleid, statusid) {
    this.applicationService
      .editstatusapplication(this.id, roleid, statusid)
      .subscribe(() => this.router.navigate(['/#']));
    console.log(roleid);
    console.log(statusid);
  }
}
