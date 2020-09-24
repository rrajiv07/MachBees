import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserPersonalDetails, UserPersonalDetails } from 'app/shared/model/user-personal-details.model';
import { UserPersonalDetailsService } from './user-personal-details.service';
import { UserPersonalDetailsComponent } from './user-personal-details.component';
import { UserPersonalDetailsDetailComponent } from './user-personal-details-detail.component';
import { UserPersonalDetailsUpdateComponent } from './user-personal-details-update.component';

@Injectable({ providedIn: 'root' })
export class UserPersonalDetailsResolve implements Resolve<IUserPersonalDetails> {
  constructor(private service: UserPersonalDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserPersonalDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userPersonalDetails: HttpResponse<UserPersonalDetails>) => {
          if (userPersonalDetails.body) {
            return of(userPersonalDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserPersonalDetails());
  }
}

export const userPersonalDetailsRoute: Routes = [
  {
    path: '',
    component: UserPersonalDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserPersonalDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserPersonalDetailsDetailComponent,
    resolve: {
      userPersonalDetails: UserPersonalDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserPersonalDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserPersonalDetailsUpdateComponent,
    resolve: {
      userPersonalDetails: UserPersonalDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserPersonalDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserPersonalDetailsUpdateComponent,
    resolve: {
      userPersonalDetails: UserPersonalDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserPersonalDetails',
    },
    canActivate: [UserRouteAccessService],
  },
];
