import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectTypeMaster } from 'app/shared/model/project-type-master.model';
import { ProjectTypeMasterService } from './project-type-master.service';

@Component({
  templateUrl: './project-type-master-delete-dialog.component.html',
})
export class ProjectTypeMasterDeleteDialogComponent {
  projectTypeMaster?: IProjectTypeMaster;

  constructor(
    protected projectTypeMasterService: ProjectTypeMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectTypeMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectTypeMasterListModification');
      this.activeModal.close();
    });
  }
}
