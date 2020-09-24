import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectRoleMaster } from 'app/shared/model/project-role-master.model';
import { ProjectRoleMasterService } from './project-role-master.service';

@Component({
  templateUrl: './project-role-master-delete-dialog.component.html',
})
export class ProjectRoleMasterDeleteDialogComponent {
  projectRoleMaster?: IProjectRoleMaster;

  constructor(
    protected projectRoleMasterService: ProjectRoleMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectRoleMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectRoleMasterListModification');
      this.activeModal.close();
    });
  }
}
