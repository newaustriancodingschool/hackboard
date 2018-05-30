import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  templateUrl: './project-view.component.html'
})
export class ProjectViewComponent implements OnInit {
  project: ProjectDto = { id: 0, title: '', description: '' };
  constructor(
    private projectService: ProjectService,
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
}
