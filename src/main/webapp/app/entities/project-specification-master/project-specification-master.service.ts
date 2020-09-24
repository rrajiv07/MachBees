import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';

type EntityResponseType = HttpResponse<IProjectSpecificationMaster>;
type EntityArrayResponseType = HttpResponse<IProjectSpecificationMaster[]>;

@Injectable({ providedIn: 'root' })
export class ProjectSpecificationMasterService {
  public resourceUrl = SERVER_API_URL + 'api/project-specification-masters';

  constructor(protected http: HttpClient) {}

  create(projectSpecificationMaster: IProjectSpecificationMaster): Observable<EntityResponseType> {
    return this.http.post<IProjectSpecificationMaster>(this.resourceUrl, projectSpecificationMaster, { observe: 'response' });
  }

  update(projectSpecificationMaster: IProjectSpecificationMaster): Observable<EntityResponseType> {
    return this.http.put<IProjectSpecificationMaster>(this.resourceUrl, projectSpecificationMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectSpecificationMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectSpecificationMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
