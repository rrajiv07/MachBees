import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';
import { ProjectFeatureMasterService } from './project-feature-master.service';

@Component({
  templateUrl: './project-feature-master-delete-dialog.component.html',
})
export class ProjectFeatureMasterDeleteDialogComponent {
  projectFeatureMaster?: IProjectFeatureMaster;

  constructor(
    protected projectFeatureMasterService: ProjectFeatureMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectFeatureMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectFeatureMasterListModification');
      this.activeModal.close();
    });
  }
}
