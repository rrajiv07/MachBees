import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';
import { ProjectAttachmentDtlService } from './project-attachment-dtl.service';

@Component({
  templateUrl: './project-attachment-dtl-delete-dialog.component.html',
})
export class ProjectAttachmentDtlDeleteDialogComponent {
  projectAttachmentDtl?: IProjectAttachmentDtl;

  constructor(
    protected projectAttachmentDtlService: ProjectAttachmentDtlService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectAttachmentDtlService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectAttachmentDtlListModification');
      this.activeModal.close();
    });
  }
}
