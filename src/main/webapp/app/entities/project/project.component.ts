import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Project } from './project.model';
import { ProjectService } from './project.service';
import { Principal } from '../../shared';

@Component({
  selector: 'jhi-project',
  templateUrl: './project.component.html'
})
export class ProjectComponent implements OnInit, OnDestroy {
  projects: Project[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    private projectService: ProjectService,
    private jhiAlertService: JhiAlertService,
    private eventManager: JhiEventManager,
    private principal: Principal
  ) {}

  loadAll() {
    this.projectService.query().subscribe(
      (res: HttpResponse<Project[]>) => {
        this.projects = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }
  ngOnInit() {
    this.loadAll();
    this.principal.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInProjects();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: Project) {
    return item.id;
  }
  registerChangeInProjects() {
    this.eventSubscriber = this.eventManager.subscribe('projectListModification', response =>
      this.loadAll()
    );
  }

  private onError(error) {
    this.jhiAlertService.error(error.message, null, null);
  }
}
