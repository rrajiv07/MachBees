import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISkillCategoryMaster, SkillCategoryMaster } from 'app/shared/model/skill-category-master.model';
import { SkillCategoryMasterService } from './skill-category-master.service';
import { SkillCategoryMasterComponent } from './skill-category-master.component';
import { SkillCategoryMasterDetailComponent } from './skill-category-master-detail.component';
import { SkillCategoryMasterUpdateComponent } from './skill-category-master-update.component';

@Injectable({ providedIn: 'root' })
export class SkillCategoryMasterResolve implements Resolve<ISkillCategoryMaster> {
  constructor(private service: SkillCategoryMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISkillCategoryMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((skillCategoryMaster: HttpResponse<SkillCategoryMaster>) => {
          if (skillCategoryMaster.body) {
            return of(skillCategoryMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SkillCategoryMaster());
  }
}

export const skillCategoryMasterRoute: Routes = [
  {
    path: '',
    component: SkillCategoryMasterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SkillCategoryMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SkillCategoryMasterDetailComponent,
    resolve: {
      skillCategoryMaster: SkillCategoryMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SkillCategoryMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SkillCategoryMasterUpdateComponent,
    resolve: {
      skillCategoryMaster: SkillCategoryMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SkillCategoryMasters',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SkillCategoryMasterUpdateComponent,
    resolve: {
      skillCategoryMaster: SkillCategoryMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SkillCategoryMasters',
    },
    canActivate: [UserRouteAccessService],
  },
];
