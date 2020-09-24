import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';

type EntityResponseType = HttpResponse<IProjectRoleDtl>;
type EntityArrayResponseType = HttpResponse<IProjectRoleDtl[]>;

@Injectable({ providedIn: 'root' })
export class ProjectRoleDtlService {
  public resourceUrl = SERVER_API_URL + 'api/project-role-dtls';

  constructor(protected http: HttpClient) {}

  create(projectRoleDtl: IProjectRoleDtl): Observable<EntityResponseType> {
    return this.http.post<IProjectRoleDtl>(this.resourceUrl, projectRoleDtl, { observe: 'response' });
  }

  update(projectRoleDtl: IProjectRoleDtl): Observable<EntityResponseType> {
    return this.http.put<IProjectRoleDtl>(this.resourceUrl, projectRoleDtl, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectRoleDtl>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectRoleDtl[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
