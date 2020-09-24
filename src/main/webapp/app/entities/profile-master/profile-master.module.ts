import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { ProfileMasterComponent } from './profile-master.component';
import { ProfileMasterDetailComponent } from './profile-master-detail.component';
import { ProfileMasterUpdateComponent } from './profile-master-update.component';
import { ProfileMasterDeleteDialogComponent } from './profile-master-delete-dialog.component';
import { profileMasterRoute } from './profile-master.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(profileMasterRoute)],
  declarations: [ProfileMasterComponent, ProfileMasterDetailComponent, ProfileMasterUpdateComponent, ProfileMasterDeleteDialogComponent],
  entryComponents: [ProfileMasterDeleteDialogComponent],
})
export class MachBeesProfileMasterModule {}
