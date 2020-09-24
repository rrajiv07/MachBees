import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectSpecificationMaster, ProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';
import { ProjectSpecificationMasterService } from './project-specification-master.service';
import { ProjectSpecificationMasterComponent } from './project-specification-master.component';
import { ProjectSpecificationMasterDetailComponent } from './project-specification-master-detail.component';
import { ProjectSpecificationMasterUpdateComponent } from './project-specification-master-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectSpecificationMasterResolve implements Resolve<IProjectSpecificationMaster> {
  constructor(private service: ProjectSpecificationMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectSpecificationMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectSpecificationMaster: HttpResponse<ProjectSpecificationMaster>) => {
          if (projectSpecificationMaster.body) {
            return of(projectSpecificationMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectSpecificationMaster());
  }
}

export const projectSpecificationMasterRoute: Routes = [
  {
    path: '',
    component: ProjectSpecificationMasterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectSpecificationMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectSpecificationMasterDetailComponent,
    resolve: {
      projectSpecificationMaster: ProjectSpecificationMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectSpecificationMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectSpecificationMasterUpdateComponent,
    resolve: {
      projectSpecificationMaster: ProjectSpecificationMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectSpecificationMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectSpecificationMasterUpdateComponent,
    resolve: {
      projectSpecificationMaster: ProjectSpecificationMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectSpecificationMasters',
    },
    canActivate: [UserRouteAccessService],
  },
];
