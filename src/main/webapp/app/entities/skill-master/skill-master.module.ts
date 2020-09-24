import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { SkillMasterComponent } from './skill-master.component';
import { SkillMasterDetailComponent } from './skill-master-detail.component';
import { SkillMasterUpdateComponent } from './skill-master-update.component';
import { SkillMasterDeleteDialogComponent } from './skill-master-delete-dialog.component';
import { skillMasterRoute } from './skill-master.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(skillMasterRoute)],
  declarations: [SkillMasterComponent, SkillMasterDetailComponent, SkillMasterUpdateComponent, SkillMasterDeleteDialogComponent],
  entryComponents: [SkillMasterDeleteDialogComponent],
})
export class MachBeesSkillMasterModule {}
