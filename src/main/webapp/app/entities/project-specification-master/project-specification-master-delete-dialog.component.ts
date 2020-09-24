import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';
import { ProjectSpecificationMasterService } from './project-specification-master.service';

@Component({
  templateUrl: './project-specification-master-delete-dialog.component.html',
})
export class ProjectSpecificationMasterDeleteDialogComponent {
  projectSpecificationMaster?: IProjectSpecificationMaster;

  constructor(
    protected projectSpecificationMasterService: ProjectSpecificationMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectSpecificationMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectSpecificationMasterListModification');
      this.activeModal.close();
    });
  }
}
