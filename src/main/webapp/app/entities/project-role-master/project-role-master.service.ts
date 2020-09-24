import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectRoleMaster } from 'app/shared/model/project-role-master.model';

type EntityResponseType = HttpResponse<IProjectRoleMaster>;
type EntityArrayResponseType = HttpResponse<IProjectRoleMaster[]>;

@Injectable({ providedIn: 'root' })
export class ProjectRoleMasterService {
  public resourceUrl = SERVER_API_URL + 'api/project-role-masters';

  constructor(protected http: HttpClient) {}

  create(projectRoleMaster: IProjectRoleMaster): Observable<EntityResponseType> {
    return this.http.post<IProjectRoleMaster>(this.resourceUrl, projectRoleMaster, { observe: 'response' });
  }

  update(projectRoleMaster: IProjectRoleMaster): Observable<EntityResponseType> {
    return this.http.put<IProjectRoleMaster>(this.resourceUrl, projectRoleMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectRoleMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectRoleMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
