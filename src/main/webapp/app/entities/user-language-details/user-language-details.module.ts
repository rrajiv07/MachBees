import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { UserLanguageDetailsComponent } from './user-language-details.component';
import { UserLanguageDetailsDetailComponent } from './user-language-details-detail.component';
import { UserLanguageDetailsUpdateComponent } from './user-language-details-update.component';
import { UserLanguageDetailsDeleteDialogComponent } from './user-language-details-delete-dialog.component';
import { userLanguageDetailsRoute } from './user-language-details.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(userLanguageDetailsRoute)],
  declarations: [
    UserLanguageDetailsComponent,
    UserLanguageDetailsDetailComponent,
    UserLanguageDetailsUpdateComponent,
    UserLanguageDetailsDeleteDialogComponent,
  ],
  entryComponents: [UserLanguageDetailsDeleteDialogComponent],
})
export class MachBeesUserLanguageDetailsModule {}
