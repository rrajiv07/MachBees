import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProfileMaster, ProfileMaster } from 'app/shared/model/profile-master.model';
import { ProfileMasterService } from './profile-master.service';
import { ProfileMasterComponent } from './profile-master.component';
import { ProfileMasterDetailComponent } from './profile-master-detail.component';
import { ProfileMasterUpdateComponent } from './profile-master-update.component';

@Injectable({ providedIn: 'root' })
export class ProfileMasterResolve implements Resolve<IProfileMaster> {
  constructor(private service: ProfileMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProfileMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((profileMaster: HttpResponse<ProfileMaster>) => {
          if (profileMaster.body) {
            return of(profileMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProfileMaster());
  }
}

export const profileMasterRoute: Routes = [
  {
    path: '',
    component: ProfileMasterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProfileMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProfileMasterDetailComponent,
    resolve: {
      profileMaster: ProfileMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProfileMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProfileMasterUpdateComponent,
    resolve: {
      profileMaster: ProfileMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProfileMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProfileMasterUpdateComponent,
    resolve: {
      profileMaster: ProfileMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProfileMasters',
    },
    canActivate: [UserRouteAccessService],
  },
];
