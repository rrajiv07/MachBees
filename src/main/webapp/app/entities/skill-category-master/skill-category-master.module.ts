import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { SkillCategoryMasterComponent } from './skill-category-master.component';
import { SkillCategoryMasterDetailComponent } from './skill-category-master-detail.component';
import { SkillCategoryMasterUpdateComponent } from './skill-category-master-update.component';
import { SkillCategoryMasterDeleteDialogComponent } from './skill-category-master-delete-dialog.component';
import { skillCategoryMasterRoute } from './skill-category-master.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(skillCategoryMasterRoute)],
  declarations: [
    SkillCategoryMasterComponent,
    SkillCategoryMasterDetailComponent,
    SkillCategoryMasterUpdateComponent,
    SkillCategoryMasterDeleteDialogComponent,
  ],
  entryComponents: [SkillCategoryMasterDeleteDialogComponent],
})
export class MachBeesSkillCategoryMasterModule {}
