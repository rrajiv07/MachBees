import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';

type EntityResponseType = HttpResponse<IProjectSkillDtl>;
type EntityArrayResponseType = HttpResponse<IProjectSkillDtl[]>;

@Injectable({ providedIn: 'root' })
export class ProjectSkillDtlService {
  public resourceUrl = SERVER_API_URL + 'api/project-skill-dtls';

  constructor(protected http: HttpClient) {}

  create(projectSkillDtl: IProjectSkillDtl): Observable<EntityResponseType> {
    return this.http.post<IProjectSkillDtl>(this.resourceUrl, projectSkillDtl, { observe: 'response' });
  }

  update(projectSkillDtl: IProjectSkillDtl): Observable<EntityResponseType> {
    return this.http.put<IProjectSkillDtl>(this.resourceUrl, projectSkillDtl, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectSkillDtl>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectSkillDtl[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
