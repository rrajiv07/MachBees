import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';

type EntityResponseType = HttpResponse<IProjectFeatureDtl>;
type EntityArrayResponseType = HttpResponse<IProjectFeatureDtl[]>;

@Injectable({ providedIn: 'root' })
export class ProjectFeatureDtlService {
  public resourceUrl = SERVER_API_URL + 'api/project-feature-dtls';

  constructor(protected http: HttpClient) {}

  create(projectFeatureDtl: IProjectFeatureDtl): Observable<EntityResponseType> {
    return this.http.post<IProjectFeatureDtl>(this.resourceUrl, projectFeatureDtl, { observe: 'response' });
  }

  update(projectFeatureDtl: IProjectFeatureDtl): Observable<EntityResponseType> {
    return this.http.put<IProjectFeatureDtl>(this.resourceUrl, projectFeatureDtl, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectFeatureDtl>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectFeatureDtl[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
