import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectAttachmentDtlComponent } from './project-attachment-dtl.component';
import { ProjectAttachmentDtlDetailComponent } from './project-attachment-dtl-detail.component';
import { ProjectAttachmentDtlUpdateComponent } from './project-attachment-dtl-update.component';
import { ProjectAttachmentDtlDeleteDialogComponent } from './project-attachment-dtl-delete-dialog.component';
import { projectAttachmentDtlRoute } from './project-attachment-dtl.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectAttachmentDtlRoute)],
  declarations: [
    ProjectAttachmentDtlComponent,
    ProjectAttachmentDtlDetailComponent,
    ProjectAttachmentDtlUpdateComponent,
    ProjectAttachmentDtlDeleteDialogComponent,
  ],
  entryComponents: [ProjectAttachmentDtlDeleteDialogComponent],
})
export class MachBeesProjectAttachmentDtlModule {}
