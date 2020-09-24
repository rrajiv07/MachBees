import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectRoleMaster, ProjectRoleMaster } from 'app/shared/model/project-role-master.model';
import { ProjectRoleMasterService } from './project-role-master.service';
import { ProjectRoleMasterComponent } from './project-role-master.component';
import { ProjectRoleMasterDetailComponent } from './project-role-master-detail.component';
import { ProjectRoleMasterUpdateComponent } from './project-role-master-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectRoleMasterResolve implements Resolve<IProjectRoleMaster> {
  constructor(private service: ProjectRoleMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectRoleMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectRoleMaster: HttpResponse<ProjectRoleMaster>) => {
          if (projectRoleMaster.body) {
            return of(projectRoleMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectRoleMaster());
  }
}

export const projectRoleMasterRoute: Routes = [
  {
    path: '',
    component: ProjectRoleMasterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectRoleMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectRoleMasterDetailComponent,
    resolve: {
      projectRoleMaster: ProjectRoleMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectRoleMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectRoleMasterUpdateComponent,
    resolve: {
      projectRoleMaster: ProjectRoleMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectRoleMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectRoleMasterUpdateComponent,
    resolve: {
      projectRoleMaster: ProjectRoleMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectRoleMasters',
    },
    canActivate: [UserRouteAccessService],
  },
];
