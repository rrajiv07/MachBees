import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISkillMaster } from 'app/shared/model/skill-master.model';

type EntityResponseType = HttpResponse<ISkillMaster>;
type EntityArrayResponseType = HttpResponse<ISkillMaster[]>;

@Injectable({ providedIn: 'root' })
export class SkillMasterService {
  public resourceUrl = SERVER_API_URL + 'api/skill-masters';

  constructor(protected http: HttpClient) {}

  create(skillMaster: ISkillMaster): Observable<EntityResponseType> {
    return this.http.post<ISkillMaster>(this.resourceUrl, skillMaster, { observe: 'response' });
  }

  update(skillMaster: ISkillMaster): Observable<EntityResponseType> {
    return this.http.put<ISkillMaster>(this.resourceUrl, skillMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISkillMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISkillMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
