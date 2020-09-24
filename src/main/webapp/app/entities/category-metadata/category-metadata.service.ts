import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';

type EntityResponseType = HttpResponse<ICategoryMetadata>;
type EntityArrayResponseType = HttpResponse<ICategoryMetadata[]>;

@Injectable({ providedIn: 'root' })
export class CategoryMetadataService {
  public resourceUrl = SERVER_API_URL + 'api/category-metadata';

  constructor(protected http: HttpClient) {}

  create(categoryMetadata: ICategoryMetadata): Observable<EntityResponseType> {
    return this.http.post<ICategoryMetadata>(this.resourceUrl, categoryMetadata, { observe: 'response' });
  }

  update(categoryMetadata: ICategoryMetadata): Observable<EntityResponseType> {
    return this.http.put<ICategoryMetadata>(this.resourceUrl, categoryMetadata, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategoryMetadata>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategoryMetadata[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
