import './vendor.ts';

import { Injector, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Ng2Webstorage } from 'ngx-webstorage';
import { JhiEventManager } from 'ng-jhipster';

import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { HackboardSharedModule, UserRouteAccessService } from './shared';
import { HackboardAppRoutingModule } from './app-routing.module';
import { HackboardHomeModule } from './home/home.module';
import { HackboardAdminModule } from './admin/admin.module';
import { HackboardAccountModule } from './account/account.module';
import { PaginationConfig } from './blocks/config/uib-pagination.config';
import { StateStorageService } from './shared/auth/state-storage.service';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import {
  ActiveMenuDirective,
  ErrorComponent,
  FooterComponent,
  JhiMainComponent,
  NavbarComponent,
  ProfileService
} from './layouts';
import { ApiModule, Configuration } from './api';
import { HackboardProjectsModule } from './projects/projects.module';
import { XsrfInterceptor } from './blocks/interceptor/XsrfInterceptor';
import { UserDetailsComponent } from './user-details/user-details.component';

export function apiConfig() {
  return new Configuration({ basePath: location.origin });
}

@NgModule({
  imports: [
    ApiModule.forRoot(apiConfig),
    BrowserModule,
    HackboardAppRoutingModule,
    Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-' }),
    HackboardSharedModule,
    HackboardHomeModule,
    HackboardAdminModule,
    HackboardAccountModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    HackboardProjectsModule
  ],
  declarations: [
    JhiMainComponent,
    NavbarComponent,
    ErrorComponent,
    ActiveMenuDirective,
    FooterComponent,
    UserDetailsComponent
  ],
  providers: [
    PaginationConfig,
    ProfileService,
    UserRouteAccessService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true,
      deps: [StateStorageService, Injector]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandlerInterceptor,
      multi: true,
      deps: [JhiEventManager]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: NotificationInterceptor,
      multi: true,
      deps: [Injector]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: XsrfInterceptor,
      multi: true
    }
  ],
  bootstrap: [JhiMainComponent]
})
export class HackboardAppModule {}
