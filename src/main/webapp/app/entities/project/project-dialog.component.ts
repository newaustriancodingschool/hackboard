import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Project } from './project.model';
import { ProjectPopupService } from './project-popup.service';
import { ProjectService } from './project.service';
import { User, UserService } from '../../shared';

@Component({
  selector: 'jhi-project-dialog',
  templateUrl: './project-dialog.component.html'
})
export class ProjectDialogComponent implements OnInit {
  project: Project;
  isSaving: boolean;

  users: User[];

  constructor(
    public activeModal: NgbActiveModal,
    private jhiAlertService: JhiAlertService,
    private projectService: ProjectService,
    private userService: UserService,
    private eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.userService.query().subscribe(
      (res: HttpResponse<User[]>) => {
        this.users = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  clear() {
    this.activeModal.dismiss('cancel');
  }

  save() {
    this.isSaving = true;
    if (this.project.id !== undefined) {
      this.subscribeToSaveResponse(this.projectService.update(this.project));
    } else {
      this.subscribeToSaveResponse(this.projectService.create(this.project));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<Project>>) {
    result.subscribe(
      (res: HttpResponse<Project>) => this.onSaveSuccess(res.body),
      (res: HttpErrorResponse) => this.onSaveError()
    );
  }

  private onSaveSuccess(result: Project) {
    this.eventManager.broadcast({ name: 'projectListModification', content: 'OK' });
    this.isSaving = false;
    this.activeModal.dismiss(result);
  }

  private onSaveError() {
    this.isSaving = false;
  }

  private onError(error: any) {
    this.jhiAlertService.error(error.message, null, null);
  }

  trackUserById(index: number, item: User) {
    return item.id;
  }
}

@Component({
  selector: 'jhi-project-popup',
  template: ''
})
export class ProjectPopupComponent implements OnInit, OnDestroy {
  routeSub: any;

  constructor(private route: ActivatedRoute, private projectPopupService: ProjectPopupService) {}

  ngOnInit() {
    this.routeSub = this.route.params.subscribe(params => {
      if (params['id']) {
        this.projectPopupService.open(ProjectDialogComponent as Component, params['id']);
      } else {
        this.projectPopupService.open(ProjectDialogComponent as Component);
      }
    });
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }
}
