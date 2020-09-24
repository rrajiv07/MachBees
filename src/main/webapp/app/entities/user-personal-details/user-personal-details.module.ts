import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { UserPersonalDetailsComponent } from './user-personal-details.component';
import { UserPersonalDetailsDetailComponent } from './user-personal-details-detail.component';
import { UserPersonalDetailsUpdateComponent } from './user-personal-details-update.component';
import { UserPersonalDetailsDeleteDialogComponent } from './user-personal-details-delete-dialog.component';
import { userPersonalDetailsRoute } from './user-personal-details.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(userPersonalDetailsRoute)],
  declarations: [
    UserPersonalDetailsComponent,
    UserPersonalDetailsDetailComponent,
    UserPersonalDetailsUpdateComponent,
    UserPersonalDetailsDeleteDialogComponent,
  ],
  entryComponents: [UserPersonalDetailsDeleteDialogComponent],
})
export class MachBeesUserPersonalDetailsModule {}
