import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-list',
  templateUrl: './list.component.html',
  styles: []
})
export class ProjectListComponent implements OnInit {
  constructor(private projectService: ProjectService, private router: Router) {}

  ngOnInit() {}

  submit() {
    this.projectService.addProject(this.data).subscribe(() => this.router.navigate(['/projects']));
  }
}
