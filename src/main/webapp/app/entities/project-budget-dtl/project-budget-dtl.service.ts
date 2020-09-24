import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectBudgetDtl } from 'app/shared/model/project-budget-dtl.model';

type EntityResponseType = HttpResponse<IProjectBudgetDtl>;
type EntityArrayResponseType = HttpResponse<IProjectBudgetDtl[]>;

@Injectable({ providedIn: 'root' })
export class ProjectBudgetDtlService {
  public resourceUrl = SERVER_API_URL + 'api/project-budget-dtls';

  constructor(protected http: HttpClient) {}

  create(projectBudgetDtl: IProjectBudgetDtl): Observable<EntityResponseType> {
    return this.http.post<IProjectBudgetDtl>(this.resourceUrl, projectBudgetDtl, { observe: 'response' });
  }

  update(projectBudgetDtl: IProjectBudgetDtl): Observable<EntityResponseType> {
    return this.http.put<IProjectBudgetDtl>(this.resourceUrl, projectBudgetDtl, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectBudgetDtl>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectBudgetDtl[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
