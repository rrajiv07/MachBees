import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectHdr } from 'app/shared/model/project-hdr.model';
import { ProjectHdrService } from './project-hdr.service';

@Component({
  templateUrl: './project-hdr-delete-dialog.component.html',
})
export class ProjectHdrDeleteDialogComponent {
  projectHdr?: IProjectHdr;

  constructor(
    protected projectHdrService: ProjectHdrService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectHdrService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectHdrListModification');
      this.activeModal.close();
    });
  }
}
