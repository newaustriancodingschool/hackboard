import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';

@Component({
  templateUrl: './project-list.component.html',
  styles: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  projects: Array<ProjectDto>;

  constructor(private projectService: ProjectService) {}

  ngOnInit() {
    this.projectService.listProjects().subscribe(projects => (this.projects = projects));
  }
  getFilledArray(count) {
    return Array(count).fill(true);
  }
}
