import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectRoleDtl, ProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';
import { ProjectRoleDtlService } from './project-role-dtl.service';
import { ProjectRoleDtlComponent } from './project-role-dtl.component';
import { ProjectRoleDtlDetailComponent } from './project-role-dtl-detail.component';
import { ProjectRoleDtlUpdateComponent } from './project-role-dtl-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectRoleDtlResolve implements Resolve<IProjectRoleDtl> {
  constructor(private service: ProjectRoleDtlService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectRoleDtl> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectRoleDtl: HttpResponse<ProjectRoleDtl>) => {
          if (projectRoleDtl.body) {
            return of(projectRoleDtl.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectRoleDtl());
  }
}

export const projectRoleDtlRoute: Routes = [
  {
    path: '',
    component: ProjectRoleDtlComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectRoleDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectRoleDtlDetailComponent,
    resolve: {
      projectRoleDtl: ProjectRoleDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectRoleDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectRoleDtlUpdateComponent,
    resolve: {
      projectRoleDtl: ProjectRoleDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectRoleDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectRoleDtlUpdateComponent,
    resolve: {
      projectRoleDtl: ProjectRoleDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectRoleDtls',
    },
    canActivate: [UserRouteAccessService],
  },
];
