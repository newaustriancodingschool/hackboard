import { Component, OnInit } from '@angular/core';
import { ProjectDto, ProjectService } from '../../api';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectRoleService } from './../../api/api/projectRole.service';
import { ProjectRoleDto } from './../../api/model/projectRoleDto';
import { TagService } from './../../api/api/tag.service';

@Component({
  templateUrl: './project-edit.component.html',
  styles: []
})
export class ProjectEditComponent implements OnInit {
  data: ProjectDto = {
    id: 0,
    title: '',
    description: '',
    github: '',
    projectRole: [],
    projectStories: [],
    tags: []
  };
  roles: Array<ProjectRoleDto>;
  projectRoles: Array<ProjectRoleDto>;
  stories: Array<string>;
  tags: Array<string> = [];
  selectedTags: Array<string> = [];

  constructor(
    private projectService: ProjectService,
    private tagservice: TagService,
    private route: ActivatedRoute,
    private router: Router,
    private projectRoleService: ProjectRoleService
  ) {}

  ngOnInit() {
    const id = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.projectService.viewProject(id).subscribe(project => {
      this.data = project;
      this.projectRoles = [];
      this.stories = [];

      this.projectRoleService.listProjectRoles().subscribe(roles => (this.roles = roles));
      this.tagservice.showAllTags().subscribe(tags => (this.tags = tags));

      this.projectRoles = this.data.projectRole;

      this.stories = this.data.projectStories;
      this.selectedTags = this.data.tags;
    });
  }

  submit() {
    this.data.projectRole = this.projectRoles;
    this.data.projectStories = this.stories;
    this.data.tags = this.selectedTags;
    this.projectService.editProject(this.data).subscribe(() => this.router.navigate(['/projects']));
  }

  editRole(roleValue) {
    const newRoleData = { roleName: '', color: '', count: 1 };
    newRoleData.roleName = roleValue;
    let tmpcolor = '';
    for (let i = 0; i < this.roles.length; i++) {
      if (this.roles[i].roleName === roleValue) {
        tmpcolor = this.roles[i].color;
      }
    }
    newRoleData.color = tmpcolor;
    let roleFound = false;
    for (let i = 0; i < this.projectRoles.length; i++) {
      if (roleValue === this.projectRoles[i].roleName) {
        this.projectRoles[i].count++;
        roleFound = true;
      }
    }
    if (roleFound === false) {
      this.projectRoles.push(newRoleData);
    }
  }

  deleteRole(role) {
    for (let i = 0; i < this.projectRoles.length; i++) {
      console.log(this.projectRoles[i].roleName);
      if (role === this.projectRoles[i].roleName) {
        if (this.projectRoles[i].count === 1) {
          this.projectRoles.splice(i, 1);
        } else {
          this.projectRoles[i].count--;
        }
      }
    }
  }

  addStory(story) {
    this.stories.push(story);
  }

  delstory(story) {
    for (let i = 0; i < this.stories.length; i++) {
      if (story === this.stories[i]) {
        this.stories.splice(i, 1);
      }
    }
  }

  getFilledArray(count) {
    return Array(count).fill(true);
  }
}
