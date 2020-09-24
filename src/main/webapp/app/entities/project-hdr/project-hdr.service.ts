import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectHdr } from 'app/shared/model/project-hdr.model';

type EntityResponseType = HttpResponse<IProjectHdr>;
type EntityArrayResponseType = HttpResponse<IProjectHdr[]>;

@Injectable({ providedIn: 'root' })
export class ProjectHdrService {
  public resourceUrl = SERVER_API_URL + 'api/project-hdrs';

  constructor(protected http: HttpClient) {}

  create(projectHdr: IProjectHdr): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(projectHdr);
    return this.http
      .post<IProjectHdr>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(projectHdr: IProjectHdr): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(projectHdr);
    return this.http
      .put<IProjectHdr>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProjectHdr>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProjectHdr[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(projectHdr: IProjectHdr): IProjectHdr {
    const copy: IProjectHdr = Object.assign({}, projectHdr, {
      endDate: projectHdr.endDate && projectHdr.endDate.isValid() ? projectHdr.endDate.format(DATE_FORMAT) : undefined,
      createdDate: projectHdr.createdDate && projectHdr.createdDate.isValid() ? projectHdr.createdDate.format(DATE_FORMAT) : undefined,
      modifiedDate: projectHdr.modifiedDate && projectHdr.modifiedDate.isValid() ? projectHdr.modifiedDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.modifiedDate = res.body.modifiedDate ? moment(res.body.modifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((projectHdr: IProjectHdr) => {
        projectHdr.endDate = projectHdr.endDate ? moment(projectHdr.endDate) : undefined;
        projectHdr.createdDate = projectHdr.createdDate ? moment(projectHdr.createdDate) : undefined;
        projectHdr.modifiedDate = projectHdr.modifiedDate ? moment(projectHdr.modifiedDate) : undefined;
      });
    }
    return res;
  }
}
