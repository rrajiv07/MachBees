import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserCompanyDetails } from 'app/shared/model/user-company-details.model';

type EntityResponseType = HttpResponse<IUserCompanyDetails>;
type EntityArrayResponseType = HttpResponse<IUserCompanyDetails[]>;

@Injectable({ providedIn: 'root' })
export class UserCompanyDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/user-company-details';

  constructor(protected http: HttpClient) {}

  create(userCompanyDetails: IUserCompanyDetails): Observable<EntityResponseType> {
    return this.http.post<IUserCompanyDetails>(this.resourceUrl, userCompanyDetails, { observe: 'response' });
  }

  update(userCompanyDetails: IUserCompanyDetails): Observable<EntityResponseType> {
    return this.http.put<IUserCompanyDetails>(this.resourceUrl, userCompanyDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserCompanyDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserCompanyDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
