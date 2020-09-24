import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectRoleMasterComponent } from './project-role-master.component';
import { ProjectRoleMasterDetailComponent } from './project-role-master-detail.component';
import { ProjectRoleMasterUpdateComponent } from './project-role-master-update.component';
import { ProjectRoleMasterDeleteDialogComponent } from './project-role-master-delete-dialog.component';
import { projectRoleMasterRoute } from './project-role-master.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectRoleMasterRoute)],
  declarations: [
    ProjectRoleMasterComponent,
    ProjectRoleMasterDetailComponent,
    ProjectRoleMasterUpdateComponent,
    ProjectRoleMasterDeleteDialogComponent,
  ],
  entryComponents: [ProjectRoleMasterDeleteDialogComponent],
})
export class MachBeesProjectRoleMasterModule {}
