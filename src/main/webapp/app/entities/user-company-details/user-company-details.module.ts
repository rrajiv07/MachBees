import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { UserCompanyDetailsComponent } from './user-company-details.component';
import { UserCompanyDetailsDetailComponent } from './user-company-details-detail.component';
import { UserCompanyDetailsUpdateComponent } from './user-company-details-update.component';
import { UserCompanyDetailsDeleteDialogComponent } from './user-company-details-delete-dialog.component';
import { userCompanyDetailsRoute } from './user-company-details.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(userCompanyDetailsRoute)],
  declarations: [
    UserCompanyDetailsComponent,
    UserCompanyDetailsDetailComponent,
    UserCompanyDetailsUpdateComponent,
    UserCompanyDetailsDeleteDialogComponent,
  ],
  entryComponents: [UserCompanyDetailsDeleteDialogComponent],
})
export class MachBeesUserCompanyDetailsModule {}
