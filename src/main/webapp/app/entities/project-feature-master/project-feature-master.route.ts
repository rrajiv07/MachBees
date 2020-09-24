import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectFeatureMaster, ProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';
import { ProjectFeatureMasterService } from './project-feature-master.service';
import { ProjectFeatureMasterComponent } from './project-feature-master.component';
import { ProjectFeatureMasterDetailComponent } from './project-feature-master-detail.component';
import { ProjectFeatureMasterUpdateComponent } from './project-feature-master-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectFeatureMasterResolve implements Resolve<IProjectFeatureMaster> {
  constructor(private service: ProjectFeatureMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectFeatureMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectFeatureMaster: HttpResponse<ProjectFeatureMaster>) => {
          if (projectFeatureMaster.body) {
            return of(projectFeatureMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectFeatureMaster());
  }
}

export const projectFeatureMasterRoute: Routes = [
  {
    path: '',
    component: ProjectFeatureMasterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectFeatureMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectFeatureMasterDetailComponent,
    resolve: {
      projectFeatureMaster: ProjectFeatureMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectFeatureMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectFeatureMasterUpdateComponent,
    resolve: {
      projectFeatureMaster: ProjectFeatureMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectFeatureMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectFeatureMasterUpdateComponent,
    resolve: {
      projectFeatureMaster: ProjectFeatureMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectFeatureMasters',
    },
    canActivate: [UserRouteAccessService],
  },
];
