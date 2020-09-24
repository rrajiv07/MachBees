import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserMaster, UserMaster } from 'app/shared/model/user-master.model';
import { UserMasterService } from './user-master.service';
import { UserMasterComponent } from './user-master.component';
import { UserMasterDetailComponent } from './user-master-detail.component';
import { UserMasterUpdateComponent } from './user-master-update.component';

@Injectable({ providedIn: 'root' })
export class UserMasterResolve implements Resolve<IUserMaster> {
  constructor(private service: UserMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userMaster: HttpResponse<UserMaster>) => {
          if (userMaster.body) {
            return of(userMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserMaster());
  }
}

export const userMasterRoute: Routes = [
  {
    path: '',
    component: UserMasterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserMasterDetailComponent,
    resolve: {
      userMaster: UserMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserMasterUpdateComponent,
    resolve: {
      userMaster: UserMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserMasterUpdateComponent,
    resolve: {
      userMaster: UserMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UserMasters',
    },
    canActivate: [UserRouteAccessService],
  },
];
