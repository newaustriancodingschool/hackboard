import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-view',
  templateUrl: './view.component.html',
  styles: []
})
export class ProjectViewComponent implements OnInit {
  private data: ProjectDto = { id: 0, title: '', description: '', ownerId: 0 };
  private id;
  constructor(private projectService: ProjectService, private router: Router) {}

  ngOnInit() {
    this.projectService.viewProject(this.id).subscribe(project => (this.data = project));
  }
}
