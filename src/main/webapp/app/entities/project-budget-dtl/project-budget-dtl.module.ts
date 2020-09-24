import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProjectBudgetDtlComponent } from './project-budget-dtl.component';
import { ProjectBudgetDtlDetailComponent } from './project-budget-dtl-detail.component';
import { ProjectBudgetDtlUpdateComponent } from './project-budget-dtl-update.component';
import { ProjectBudgetDtlDeleteDialogComponent } from './project-budget-dtl-delete-dialog.component';
import { projectBudgetDtlRoute } from './project-budget-dtl.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(projectBudgetDtlRoute)],
  declarations: [
    ProjectBudgetDtlComponent,
    ProjectBudgetDtlDetailComponent,
    ProjectBudgetDtlUpdateComponent,
    ProjectBudgetDtlDeleteDialogComponent,
  ],
  entryComponents: [ProjectBudgetDtlDeleteDialogComponent],
})
export class MachBeesProjectBudgetDtlModule {}
