import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectCategoryMaster } from 'app/shared/model/project-category-master.model';

type EntityResponseType = HttpResponse<IProjectCategoryMaster>;
type EntityArrayResponseType = HttpResponse<IProjectCategoryMaster[]>;

@Injectable({ providedIn: 'root' })
export class ProjectCategoryMasterService {
  public resourceUrl = SERVER_API_URL + 'api/project-category-masters';

  constructor(protected http: HttpClient) {}

  create(projectCategoryMaster: IProjectCategoryMaster): Observable<EntityResponseType> {
    return this.http.post<IProjectCategoryMaster>(this.resourceUrl, projectCategoryMaster, { observe: 'response' });
  }

  update(projectCategoryMaster: IProjectCategoryMaster): Observable<EntityResponseType> {
    return this.http.put<IProjectCategoryMaster>(this.resourceUrl, projectCategoryMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectCategoryMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectCategoryMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
