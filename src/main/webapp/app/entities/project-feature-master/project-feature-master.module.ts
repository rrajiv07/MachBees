import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectFeatureMasterComponent } from './project-feature-master.component';
import { ProjectFeatureMasterDetailComponent } from './project-feature-master-detail.component';
import { ProjectFeatureMasterUpdateComponent } from './project-feature-master-update.component';
import { ProjectFeatureMasterDeleteDialogComponent } from './project-feature-master-delete-dialog.component';
import { projectFeatureMasterRoute } from './project-feature-master.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectFeatureMasterRoute)],
  declarations: [
    ProjectFeatureMasterComponent,
    ProjectFeatureMasterDetailComponent,
    ProjectFeatureMasterUpdateComponent,
    ProjectFeatureMasterDeleteDialogComponent,
  ],
  entryComponents: [ProjectFeatureMasterDeleteDialogComponent],
})
export class MachBeesProjectFeatureMasterModule {}
