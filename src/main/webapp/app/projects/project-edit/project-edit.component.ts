import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  templateUrl: './project-edit.component.html',
  styles: []
})
export class ProjectEditComponent implements OnInit {
  data: ProjectDto = { id: 0, title: '', description: '', github: '' };

  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.projectService.viewProject(id).subscribe(project => {
      this.data = project;
    });
  }

  submit() {
    this.projectService.editProject(this.data).subscribe(() => this.router.navigate(['/projects']));
  }
}
