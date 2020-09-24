import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectHdrComponent } from './project-hdr.component';
import { ProjectHdrDetailComponent } from './project-hdr-detail.component';
import { ProjectHdrUpdateComponent } from './project-hdr-update.component';
import { ProjectHdrDeleteDialogComponent } from './project-hdr-delete-dialog.component';
import { projectHdrRoute } from './project-hdr.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectHdrRoute)],
  declarations: [ProjectHdrComponent, ProjectHdrDetailComponent, ProjectHdrUpdateComponent, ProjectHdrDeleteDialogComponent],
  entryComponents: [ProjectHdrDeleteDialogComponent],
})
export class MachBeesProjectHdrModule {}
