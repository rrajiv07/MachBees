import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectFeatureDtl, ProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';
import { ProjectFeatureDtlService } from './project-feature-dtl.service';
import { ProjectFeatureDtlComponent } from './project-feature-dtl.component';
import { ProjectFeatureDtlDetailComponent } from './project-feature-dtl-detail.component';
import { ProjectFeatureDtlUpdateComponent } from './project-feature-dtl-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectFeatureDtlResolve implements Resolve<IProjectFeatureDtl> {
  constructor(private service: ProjectFeatureDtlService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectFeatureDtl> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectFeatureDtl: HttpResponse<ProjectFeatureDtl>) => {
          if (projectFeatureDtl.body) {
            return of(projectFeatureDtl.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectFeatureDtl());
  }
}

export const projectFeatureDtlRoute: Routes = [
  {
    path: '',
    component: ProjectFeatureDtlComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectFeatureDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectFeatureDtlDetailComponent,
    resolve: {
      projectFeatureDtl: ProjectFeatureDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectFeatureDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectFeatureDtlUpdateComponent,
    resolve: {
      projectFeatureDtl: ProjectFeatureDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectFeatureDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectFeatureDtlUpdateComponent,
    resolve: {
      projectFeatureDtl: ProjectFeatureDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectFeatureDtls',
    },
    canActivate: [UserRouteAccessService],
  },
];
