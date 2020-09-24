import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectFeatureDtlComponent } from './project-feature-dtl.component';
import { ProjectFeatureDtlDetailComponent } from './project-feature-dtl-detail.component';
import { ProjectFeatureDtlUpdateComponent } from './project-feature-dtl-update.component';
import { ProjectFeatureDtlDeleteDialogComponent } from './project-feature-dtl-delete-dialog.component';
import { projectFeatureDtlRoute } from './project-feature-dtl.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectFeatureDtlRoute)],
  declarations: [
    ProjectFeatureDtlComponent,
    ProjectFeatureDtlDetailComponent,
    ProjectFeatureDtlUpdateComponent,
    ProjectFeatureDtlDeleteDialogComponent,
  ],
  entryComponents: [ProjectFeatureDtlDeleteDialogComponent],
})
export class MachBeesProjectFeatureDtlModule {}
