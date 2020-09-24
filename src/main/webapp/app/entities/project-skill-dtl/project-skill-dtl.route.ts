import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectSkillDtl, ProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';
import { ProjectSkillDtlService } from './project-skill-dtl.service';
import { ProjectSkillDtlComponent } from './project-skill-dtl.component';
import { ProjectSkillDtlDetailComponent } from './project-skill-dtl-detail.component';
import { ProjectSkillDtlUpdateComponent } from './project-skill-dtl-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectSkillDtlResolve implements Resolve<IProjectSkillDtl> {
  constructor(private service: ProjectSkillDtlService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectSkillDtl> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectSkillDtl: HttpResponse<ProjectSkillDtl>) => {
          if (projectSkillDtl.body) {
            return of(projectSkillDtl.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectSkillDtl());
  }
}

export const projectSkillDtlRoute: Routes = [
  {
    path: '',
    component: ProjectSkillDtlComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectSkillDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectSkillDtlDetailComponent,
    resolve: {
      projectSkillDtl: ProjectSkillDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectSkillDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectSkillDtlUpdateComponent,
    resolve: {
      projectSkillDtl: ProjectSkillDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectSkillDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectSkillDtlUpdateComponent,
    resolve: {
      projectSkillDtl: ProjectSkillDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectSkillDtls',
    },
    canActivate: [UserRouteAccessService],
  },
];
