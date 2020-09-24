import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICategoryMetadata, CategoryMetadata } from 'app/shared/model/category-metadata.model';
import { CategoryMetadataService } from './category-metadata.service';
import { CategoryMetadataComponent } from './category-metadata.component';
import { CategoryMetadataDetailComponent } from './category-metadata-detail.component';
import { CategoryMetadataUpdateComponent } from './category-metadata-update.component';

@Injectable({ providedIn: 'root' })
export class CategoryMetadataResolve implements Resolve<ICategoryMetadata> {
  constructor(private service: CategoryMetadataService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategoryMetadata> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((categoryMetadata: HttpResponse<CategoryMetadata>) => {
          if (categoryMetadata.body) {
            return of(categoryMetadata.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CategoryMetadata());
  }
}

export const categoryMetadataRoute: Routes = [
  {
    path: '',
    component: CategoryMetadataComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategoryMetadata',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CategoryMetadataDetailComponent,
    resolve: {
      categoryMetadata: CategoryMetadataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategoryMetadata',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CategoryMetadataUpdateComponent,
    resolve: {
      categoryMetadata: CategoryMetadataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategoryMetadata',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CategoryMetadataUpdateComponent,
    resolve: {
      categoryMetadata: CategoryMetadataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategoryMetadata',
    },
    canActivate: [UserRouteAccessService],
  },
];
