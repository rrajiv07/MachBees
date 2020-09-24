import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectCategoryMaster, ProjectCategoryMaster } from 'app/shared/model/project-category-master.model';
import { ProjectCategoryMasterService } from './project-category-master.service';
import { ProjectCategoryMasterComponent } from './project-category-master.component';
import { ProjectCategoryMasterDetailComponent } from './project-category-master-detail.component';
import { ProjectCategoryMasterUpdateComponent } from './project-category-master-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectCategoryMasterResolve implements Resolve<IProjectCategoryMaster> {
  constructor(private service: ProjectCategoryMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectCategoryMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectCategoryMaster: HttpResponse<ProjectCategoryMaster>) => {
          if (projectCategoryMaster.body) {
            return of(projectCategoryMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectCategoryMaster());
  }
}

export const projectCategoryMasterRoute: Routes = [
  {
    path: '',
    component: ProjectCategoryMasterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectCategoryMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectCategoryMasterDetailComponent,
    resolve: {
      projectCategoryMaster: ProjectCategoryMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectCategoryMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectCategoryMasterUpdateComponent,
    resolve: {
      projectCategoryMaster: ProjectCategoryMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectCategoryMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectCategoryMasterUpdateComponent,
    resolve: {
      projectCategoryMaster: ProjectCategoryMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectCategoryMasters',
    },
    canActivate: [UserRouteAccessService],
  },
];
