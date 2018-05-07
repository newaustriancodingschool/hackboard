import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpXsrfTokenExtractor
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class XsrfInterceptor implements HttpInterceptor {
  constructor(private tokenExtractor: HttpXsrfTokenExtractor) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let requestToForward = req;
    let token = this.tokenExtractor.getToken() as string;
    if (token !== null) {
      requestToForward = req.clone({ setHeaders: { 'X-XSRF-TOKEN': token } });
    }
    return next.handle(requestToForward);
  }
}
