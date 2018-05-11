import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';

@Component({
  templateUrl: './list.component.html',
  styles: []
})
export class ProjectListComponent implements OnInit {
  private projects: Array<ProjectDto>;
  private data: ProjectDto = { id: 0, title: '', description: '', ownerId: 0 };
  constructor(private projectService: ProjectService) {}

  ngOnInit() {
    this.projectService.listProjects().subscribe(projects => (this.projects = projects));
  }
}
