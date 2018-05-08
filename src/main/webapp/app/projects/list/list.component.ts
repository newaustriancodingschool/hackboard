import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';

@Component({
  templateUrl: './list.component.html',
  styles: []
})
export class ProjectListComponent implements OnInit {
  projects: Array<ProjectDto>;
  constructor(private projectService: ProjectService) {}

  ngOnInit() {
    this.projectService.listProjects().subscribe(projects => (this.projects = projects));
  }
}
