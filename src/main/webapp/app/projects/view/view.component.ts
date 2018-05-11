import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { Router } from '@angular/router';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  templateUrl: './view.component.html',
  styles: []
})
export class ProjectViewComponent implements OnInit {
  private project: ProjectDto;
  private selectedId: number;
  constructor(private projectService: ProjectService, private route: ActivatedRoute) {}

  ngOnInit() {
    this.project = this.route.paramMap.pipe(
      switchMap((params: ParamMap) => this.projectService.viewProject(params.get('id')))
    );
  }
}
