import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserCompanyDetails, UserCompanyDetails } from 'app/shared/model/user-company-details.model';
import { UserCompanyDetailsService } from './user-company-details.service';
import { UserCompanyDetailsComponent } from './user-company-details.component';
import { UserCompanyDetailsDetailComponent } from './user-company-details-detail.component';
import { UserCompanyDetailsUpdateComponent } from './user-company-details-update.component';

@Injectable({ providedIn: 'root' })
export class UserCompanyDetailsResolve implements Resolve<IUserCompanyDetails> {
  constructor(private service: UserCompanyDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserCompanyDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userCompanyDetails: HttpResponse<UserCompanyDetails>) => {
          if (userCompanyDetails.body) {
            return of(userCompanyDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserCompanyDetails());
  }
}

export const userCompanyDetailsRoute: Routes = [
  {
    path: '',
    component: UserCompanyDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserCompanyDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserCompanyDetailsDetailComponent,
    resolve: {
      userCompanyDetails: UserCompanyDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserCompanyDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserCompanyDetailsUpdateComponent,
    resolve: {
      userCompanyDetails: UserCompanyDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserCompanyDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserCompanyDetailsUpdateComponent,
    resolve: {
      userCompanyDetails: UserCompanyDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserCompanyDetails',
    },
    canActivate: [UserRouteAccessService],
  },
];
