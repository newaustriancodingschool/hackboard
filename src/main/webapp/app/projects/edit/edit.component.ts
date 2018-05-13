import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { Router } from '@angular/router';
import { Route, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'jhi-edit',
  templateUrl: './edit.component.html',
  styles: []
})
export class ProjectEditComponent implements OnInit {
  private data: ProjectDto = { id: 0, title: '', description: '' };

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
