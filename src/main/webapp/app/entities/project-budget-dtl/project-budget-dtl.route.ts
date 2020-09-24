import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectBudgetDtl, ProjectBudgetDtl } from 'app/shared/model/project-budget-dtl.model';
import { ProjectBudgetDtlService } from './project-budget-dtl.service';
import { ProjectBudgetDtlComponent } from './project-budget-dtl.component';
import { ProjectBudgetDtlDetailComponent } from './project-budget-dtl-detail.component';
import { ProjectBudgetDtlUpdateComponent } from './project-budget-dtl-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectBudgetDtlResolve implements Resolve<IProjectBudgetDtl> {
  constructor(private service: ProjectBudgetDtlService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectBudgetDtl> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectBudgetDtl: HttpResponse<ProjectBudgetDtl>) => {
          if (projectBudgetDtl.body) {
            return of(projectBudgetDtl.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectBudgetDtl());
  }
}

export const projectBudgetDtlRoute: Routes = [
  {
    path: '',
    component: ProjectBudgetDtlComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectBudgetDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectBudgetDtlDetailComponent,
    resolve: {
      projectBudgetDtl: ProjectBudgetDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectBudgetDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectBudgetDtlUpdateComponent,
    resolve: {
      projectBudgetDtl: ProjectBudgetDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectBudgetDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectBudgetDtlUpdateComponent,
    resolve: {
      projectBudgetDtl: ProjectBudgetDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectBudgetDtls',
    },
    canActivate: [UserRouteAccessService],
  },
];
