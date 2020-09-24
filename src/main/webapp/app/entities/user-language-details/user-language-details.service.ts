import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserLanguageDetails } from 'app/shared/model/user-language-details.model';

type EntityResponseType = HttpResponse<IUserLanguageDetails>;
type EntityArrayResponseType = HttpResponse<IUserLanguageDetails[]>;

@Injectable({ providedIn: 'root' })
export class UserLanguageDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/user-language-details';

  constructor(protected http: HttpClient) {}

  create(userLanguageDetails: IUserLanguageDetails): Observable<EntityResponseType> {
    return this.http.post<IUserLanguageDetails>(this.resourceUrl, userLanguageDetails, { observe: 'response' });
  }

  update(userLanguageDetails: IUserLanguageDetails): Observable<EntityResponseType> {
    return this.http.put<IUserLanguageDetails>(this.resourceUrl, userLanguageDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserLanguageDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserLanguageDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
