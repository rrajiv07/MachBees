import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProfileMaster } from 'app/shared/model/profile-master.model';

type EntityResponseType = HttpResponse<IProfileMaster>;
type EntityArrayResponseType = HttpResponse<IProfileMaster[]>;

@Injectable({ providedIn: 'root' })
export class ProfileMasterService {
  public resourceUrl = SERVER_API_URL + 'api/profile-masters';

  constructor(protected http: HttpClient) {}

  create(profileMaster: IProfileMaster): Observable<EntityResponseType> {
    return this.http.post<IProfileMaster>(this.resourceUrl, profileMaster, { observe: 'response' });
  }

  update(profileMaster: IProfileMaster): Observable<EntityResponseType> {
    return this.http.put<IProfileMaster>(this.resourceUrl, profileMaster, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProfileMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProfileMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
