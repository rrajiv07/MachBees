import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserPersonalDetails } from 'app/shared/model/user-personal-details.model';

type EntityResponseType = HttpResponse<IUserPersonalDetails>;
type EntityArrayResponseType = HttpResponse<IUserPersonalDetails[]>;

@Injectable({ providedIn: 'root' })
export class UserPersonalDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/user-personal-details';

  constructor(protected http: HttpClient) {}

  create(userPersonalDetails: IUserPersonalDetails): Observable<EntityResponseType> {
    return this.http.post<IUserPersonalDetails>(this.resourceUrl, userPersonalDetails, { observe: 'response' });
  }

  update(userPersonalDetails: IUserPersonalDetails): Observable<EntityResponseType> {
    return this.http.put<IUserPersonalDetails>(this.resourceUrl, userPersonalDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserPersonalDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserPersonalDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
