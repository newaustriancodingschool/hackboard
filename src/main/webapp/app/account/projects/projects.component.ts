import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';

@Component({
  selector: 'jhi-projects',
  templateUrl: './projects.component.html',
  styles: ['projects.component.css']
})
export class ProjectsComponent implements OnInit {
  private projects: Array<ProjectDto> = [];
  constructor(private projectService: ProjectService) {}

  ngOnInit() {
    this.projectService.listProjects().subscribe(projects => (this.projects = projects));
  }
}
