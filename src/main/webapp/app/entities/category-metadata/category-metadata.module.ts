import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MachBeesSharedModule } from 'app/shared/shared.module';
import { CategoryMetadataComponent } from './category-metadata.component';
import { CategoryMetadataDetailComponent } from './category-metadata-detail.component';
import { CategoryMetadataUpdateComponent } from './category-metadata-update.component';
import { CategoryMetadataDeleteDialogComponent } from './category-metadata-delete-dialog.component';
import { categoryMetadataRoute } from './category-metadata.route';

@NgModule({
  imports: [MachBeesSharedModule, RouterModule.forChild(categoryMetadataRoute)],
  declarations: [
    CategoryMetadataComponent,
    CategoryMetadataDetailComponent,
    CategoryMetadataUpdateComponent,
    CategoryMetadataDeleteDialogComponent,
  ],
  entryComponents: [CategoryMetadataDeleteDialogComponent],
})
export class MachBeesCategoryMetadataModule {}
