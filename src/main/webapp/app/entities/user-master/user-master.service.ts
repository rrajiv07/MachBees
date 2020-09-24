import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserMaster } from 'app/shared/model/user-master.model';

type EntityResponseType = HttpResponse<IUserMaster>;
type EntityArrayResponseType = HttpResponse<IUserMaster[]>;

@Injectable({ providedIn: 'root' })
export class UserMasterService {
  public resourceUrl = SERVER_API_URL + 'api/user-masters';

  constructor(protected http: HttpClient) {}

  create(userMaster: IUserMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userMaster);
    return this.http
      .post<IUserMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userMaster: IUserMaster): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userMaster);
    return this.http
      .put<IUserMaster>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUserMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserMaster[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(userMaster: IUserMaster): IUserMaster {
    const copy: IUserMaster = Object.assign({}, userMaster, {
      updatedDate: userMaster.updatedDate && userMaster.updatedDate.isValid() ? userMaster.updatedDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.updatedDate = res.body.updatedDate ? moment(res.body.updatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((userMaster: IUserMaster) => {
        userMaster.updatedDate = userMaster.updatedDate ? moment(userMaster.updatedDate) : undefined;
      });
    }
    return res;
  }
}
