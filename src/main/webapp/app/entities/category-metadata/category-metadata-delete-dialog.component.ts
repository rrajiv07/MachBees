import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';
import { CategoryMetadataService } from './category-metadata.service';

@Component({
  templateUrl: './category-metadata-delete-dialog.component.html',
})
export class CategoryMetadataDeleteDialogComponent {
  categoryMetadata?: ICategoryMetadata;

  constructor(
    protected categoryMetadataService: CategoryMetadataService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.categoryMetadataService.delete(id).subscribe(() => {
      this.eventManager.broadcast('categoryMetadataListModification');
      this.activeModal.close();
    });
  }
}
