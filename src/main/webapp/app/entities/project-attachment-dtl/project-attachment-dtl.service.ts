import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';

type EntityResponseType = HttpResponse<IProjectAttachmentDtl>;
type EntityArrayResponseType = HttpResponse<IProjectAttachmentDtl[]>;

@Injectable({ providedIn: 'root' })
export class ProjectAttachmentDtlService {
  public resourceUrl = SERVER_API_URL + 'api/project-attachment-dtls';

  constructor(protected http: HttpClient) {}

  create(projectAttachmentDtl: IProjectAttachmentDtl): Observable<EntityResponseType> {
    return this.http.post<IProjectAttachmentDtl>(this.resourceUrl, projectAttachmentDtl, { observe: 'response' });
  }

  update(projectAttachmentDtl: IProjectAttachmentDtl): Observable<EntityResponseType> {
    return this.http.put<IProjectAttachmentDtl>(this.resourceUrl, projectAttachmentDtl, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectAttachmentDtl>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectAttachmentDtl[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
