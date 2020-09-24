import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectHdr, ProjectHdr } from 'app/shared/model/project-hdr.model';
import { ProjectHdrService } from './project-hdr.service';
import { ProjectHdrComponent } from './project-hdr.component';
import { ProjectHdrDetailComponent } from './project-hdr-detail.component';
import { ProjectHdrUpdateComponent } from './project-hdr-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectHdrResolve implements Resolve<IProjectHdr> {
  constructor(private service: ProjectHdrService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectHdr> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectHdr: HttpResponse<ProjectHdr>) => {
          if (projectHdr.body) {
            return of(projectHdr.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectHdr());
  }
}

export const projectHdrRoute: Routes = [
  {
    path: '',
    component: ProjectHdrComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectHdrs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectHdrDetailComponent,
    resolve: {
      projectHdr: ProjectHdrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectHdrs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectHdrUpdateComponent,
    resolve: {
      projectHdr: ProjectHdrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectHdrs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectHdrUpdateComponent,
    resolve: {
      projectHdr: ProjectHdrResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectHdrs',
    },
    canActivate: [UserRouteAccessService],
  },
];
