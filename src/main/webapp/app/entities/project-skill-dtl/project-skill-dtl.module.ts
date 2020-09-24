import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectSkillDtlComponent } from './project-skill-dtl.component';
import { ProjectSkillDtlDetailComponent } from './project-skill-dtl-detail.component';
import { ProjectSkillDtlUpdateComponent } from './project-skill-dtl-update.component';
import { ProjectSkillDtlDeleteDialogComponent } from './project-skill-dtl-delete-dialog.component';
import { projectSkillDtlRoute } from './project-skill-dtl.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectSkillDtlRoute)],
  declarations: [
    ProjectSkillDtlComponent,
    ProjectSkillDtlDetailComponent,
    ProjectSkillDtlUpdateComponent,
    ProjectSkillDtlDeleteDialogComponent,
  ],
  entryComponents: [ProjectSkillDtlDeleteDialogComponent],
})
export class MachBeesProjectSkillDtlModule {}
