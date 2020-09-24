import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectRoleDtlComponent } from './project-role-dtl.component';
import { ProjectRoleDtlDetailComponent } from './project-role-dtl-detail.component';
import { ProjectRoleDtlUpdateComponent } from './project-role-dtl-update.component';
import { ProjectRoleDtlDeleteDialogComponent } from './project-role-dtl-delete-dialog.component';
import { projectRoleDtlRoute } from './project-role-dtl.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectRoleDtlRoute)],
  declarations: [
    ProjectRoleDtlComponent,
    ProjectRoleDtlDetailComponent,
    ProjectRoleDtlUpdateComponent,
    ProjectRoleDtlDeleteDialogComponent,
  ],
  entryComponents: [ProjectRoleDtlDeleteDialogComponent],
})
export class MachBeesProjectRoleDtlModule {}
