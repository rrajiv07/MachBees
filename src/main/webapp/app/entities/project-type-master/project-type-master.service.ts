import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectTypeMaster } from 'app/shared/model/project-type-master.model';

type EntityResponseType = HttpResponse<IProjectTypeMaster>;
type EntityArrayResponseType = HttpResponse<IProjectTypeMaster[]>;

@Injectable({ providedIn: 'root' })
export class ProjectTypeMasterService {
  public resourceUrl = SERVER_API_URL + 'api/project-type-masters';

  constructor(protected http: HttpClient) {}

  create(projectTypeMaster: IProjectTypeMaster): Observable<EntityResponseType> {
    return this.http.post<IProjectTypeMaster>(this.resourceUrl, projectTypeMaster, { observe: 'response' });
  }

  update(projectTypeMaster: IProjectTypeMaster): Observable<EntityResponseType> {
    return this.http.put<IProjectTypeMaster>(this.resourceUrl, projectTypeMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectTypeMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectTypeMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
