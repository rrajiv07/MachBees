import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectSpecificationMasterComponent } from './project-specification-master.component';
import { ProjectSpecificationMasterDetailComponent } from './project-specification-master-detail.component';
import { ProjectSpecificationMasterUpdateComponent } from './project-specification-master-update.component';
import { ProjectSpecificationMasterDeleteDialogComponent } from './project-specification-master-delete-dialog.component';
import { projectSpecificationMasterRoute } from './project-specification-master.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectSpecificationMasterRoute)],
  declarations: [
    ProjectSpecificationMasterComponent,
    ProjectSpecificationMasterDetailComponent,
    ProjectSpecificationMasterUpdateComponent,
    ProjectSpecificationMasterDeleteDialogComponent,
  ],
  entryComponents: [ProjectSpecificationMasterDeleteDialogComponent],
})
export class MachBeesProjectSpecificationMasterModule {}
