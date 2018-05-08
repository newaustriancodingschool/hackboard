import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Project } from './project.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Project>;

@Injectable()
export class ProjectService {
  private resourceUrl = SERVER_API_URL + 'api/projects';

  constructor(private http: HttpClient) {}

  create(project: Project): Observable<EntityResponseType> {
    const copy = this.convert(project);
    return this.http
      .post<Project>(this.resourceUrl, copy, { observe: 'response' })
      .map((res: EntityResponseType) => this.convertResponse(res));
  }

  update(project: Project): Observable<EntityResponseType> {
    const copy = this.convert(project);
    return this.http
      .put<Project>(this.resourceUrl, copy, { observe: 'response' })
      .map((res: EntityResponseType) => this.convertResponse(res));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<Project>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .map((res: EntityResponseType) => this.convertResponse(res));
  }

  query(req?: any): Observable<HttpResponse<Project[]>> {
    const options = createRequestOption(req);
    return this.http
      .get<Project[]>(this.resourceUrl, { params: options, observe: 'response' })
      .map((res: HttpResponse<Project[]>) => this.convertArrayResponse(res));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  private convertResponse(res: EntityResponseType): EntityResponseType {
    const body: Project = this.convertItemFromServer(res.body);
    return res.clone({ body });
  }

  private convertArrayResponse(res: HttpResponse<Project[]>): HttpResponse<Project[]> {
    const jsonResponse: Project[] = res.body;
    const body: Project[] = [];
    for (let i = 0; i < jsonResponse.length; i++) {
      body.push(this.convertItemFromServer(jsonResponse[i]));
    }
    return res.clone({ body });
  }

  /**
   * Convert a returned JSON object to Project.
   */
  private convertItemFromServer(project: Project): Project {
    const copy: Project = Object.assign({}, project);
    return copy;
  }

  /**
   * Convert a Project to a JSON which can be sent to the server.
   */
  private convert(project: Project): Project {
    const copy: Project = Object.assign({}, project);
    return copy;
  }
}
