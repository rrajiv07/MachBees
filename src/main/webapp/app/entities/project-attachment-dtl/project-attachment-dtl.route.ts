import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectAttachmentDtl, ProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';
import { ProjectAttachmentDtlService } from './project-attachment-dtl.service';
import { ProjectAttachmentDtlComponent } from './project-attachment-dtl.component';
import { ProjectAttachmentDtlDetailComponent } from './project-attachment-dtl-detail.component';
import { ProjectAttachmentDtlUpdateComponent } from './project-attachment-dtl-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectAttachmentDtlResolve implements Resolve<IProjectAttachmentDtl> {
  constructor(private service: ProjectAttachmentDtlService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectAttachmentDtl> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectAttachmentDtl: HttpResponse<ProjectAttachmentDtl>) => {
          if (projectAttachmentDtl.body) {
            return of(projectAttachmentDtl.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectAttachmentDtl());
  }
}

export const projectAttachmentDtlRoute: Routes = [
  {
    path: '',
    component: ProjectAttachmentDtlComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectAttachmentDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectAttachmentDtlDetailComponent,
    resolve: {
      projectAttachmentDtl: ProjectAttachmentDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectAttachmentDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectAttachmentDtlUpdateComponent,
    resolve: {
      projectAttachmentDtl: ProjectAttachmentDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectAttachmentDtls',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectAttachmentDtlUpdateComponent,
    resolve: {
      projectAttachmentDtl: ProjectAttachmentDtlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProjectAttachmentDtls',
    },
    canActivate: [UserRouteAccessService],
  },
];
