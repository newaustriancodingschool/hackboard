import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { ActivatedRoute } from '@angular/router';

@Component({
  templateUrl: './view.component.html',
  styles: []
})
export class ProjectViewComponent implements OnInit {
  data: ProjectDto = { id: 0, title: '', description: '' };

  constructor(private projectService: ProjectService, private route: ActivatedRoute) {}

  ngOnInit() {
    const id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.projectService.viewProject(id).subscribe(project => {
      this.data = project;
    });
  }
}
