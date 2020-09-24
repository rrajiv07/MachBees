import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectCategoryMasterComponent } from './project-category-master.component';
import { ProjectCategoryMasterDetailComponent } from './project-category-master-detail.component';
import { ProjectCategoryMasterUpdateComponent } from './project-category-master-update.component';
import { ProjectCategoryMasterDeleteDialogComponent } from './project-category-master-delete-dialog.component';
import { projectCategoryMasterRoute } from './project-category-master.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectCategoryMasterRoute)],
  declarations: [
    ProjectCategoryMasterComponent,
    ProjectCategoryMasterDetailComponent,
    ProjectCategoryMasterUpdateComponent,
    ProjectCategoryMasterDeleteDialogComponent,
  ],
  entryComponents: [ProjectCategoryMasterDeleteDialogComponent],
})
export class MachBeesProjectCategoryMasterModule {}
