import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectTypeMasterComponent } from './project-type-master.component';
import { ProjectTypeMasterDetailComponent } from './project-type-master-detail.component';
import { ProjectTypeMasterUpdateComponent } from './project-type-master-update.component';
import { ProjectTypeMasterDeleteDialogComponent } from './project-type-master-delete-dialog.component';
import { projectTypeMasterRoute } from './project-type-master.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectTypeMasterRoute)],
  declarations: [
    ProjectTypeMasterComponent,
    ProjectTypeMasterDetailComponent,
    ProjectTypeMasterUpdateComponent,
    ProjectTypeMasterDeleteDialogComponent,
  ],
  entryComponents: [ProjectTypeMasterDeleteDialogComponent],
})
export class MachBeesProjectTypeMasterModule {}
