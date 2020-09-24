import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { UserMasterComponent } from './user-master.component';
import { UserMasterDetailComponent } from './user-master-detail.component';
import { UserMasterUpdateComponent } from './user-master-update.component';
import { UserMasterDeleteDialogComponent } from './user-master-delete-dialog.component';
import { userMasterRoute } from './user-master.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(userMasterRoute)],
  declarations: [UserMasterComponent, UserMasterDetailComponent, UserMasterUpdateComponent, UserMasterDeleteDialogComponent],
  entryComponents: [UserMasterDeleteDialogComponent],
})
export class MachBeesUserMasterModule {}
