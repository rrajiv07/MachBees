import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectTypeMaster, ProjectTypeMaster } from 'app/shared/model/project-type-master.model';
import { ProjectTypeMasterService } from './project-type-master.service';
import { ProjectTypeMasterComponent } from './project-type-master.component';
import { ProjectTypeMasterDetailComponent } from './project-type-master-detail.component';
import { ProjectTypeMasterUpdateComponent } from './project-type-master-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectTypeMasterResolve implements Resolve<IProjectTypeMaster> {
  constructor(private service: ProjectTypeMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectTypeMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectTypeMaster: HttpResponse<ProjectTypeMaster>) => {
          if (projectTypeMaster.body) {
            return of(projectTypeMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectTypeMaster());
  }
}

export const projectTypeMasterRoute: Routes = [
  {
    path: '',
    component: ProjectTypeMasterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectTypeMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectTypeMasterDetailComponent,
    resolve: {
      projectTypeMaster: ProjectTypeMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectTypeMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectTypeMasterUpdateComponent,
    resolve: {
      projectTypeMaster: ProjectTypeMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectTypeMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectTypeMasterUpdateComponent,
    resolve: {
      projectTypeMaster: ProjectTypeMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectTypeMasters',
    },
    canActivate: [UserRouteAccessService],
  },
];
