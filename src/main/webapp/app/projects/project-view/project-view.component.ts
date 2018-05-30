import { ApplicationDto } from './../../api/model/applicationDto';
import { ApplicationService } from './../../api/api/application.service';
import { ApplicantService } from './../../api/api/applicant.service';
import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  templateUrl: './project-view.component.html'
})
export class ProjectViewComponent implements OnInit {
  project: ProjectDto = { id: 0, title: '', description: '' };
  applicant: ApplicationDto = { applicant: 0, projectId: 0, roleId: 0 };
  constructor(
    private projectService: ProjectService,
    private applicationService: ApplicationService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.projectService.viewProject(id).subscribe(project => (this.project = project));
  }

  getFilledArray(count) {
    return Array(count).fill(true);
  }

  delete() {
    this.projectService
      .deleteProject(this.project.id)
      .subscribe(() => this.router.navigate(['/projects']));
  }
  apply() {
    this.applicant.projectId = 5;
    this.applicant.roleId = 4;
    this.applicant.applicant = 3;
    this.applicationService
      .addapplication(this.applicant)
      .subscribe(() => this.router.navigate(['/projects']));
  }
}
