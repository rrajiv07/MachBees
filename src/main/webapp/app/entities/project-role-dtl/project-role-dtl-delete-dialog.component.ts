import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';
import { ProjectRoleDtlService } from './project-role-dtl.service';

@Component({
  templateUrl: './project-role-dtl-delete-dialog.component.html',
})
export class ProjectRoleDtlDeleteDialogComponent {
  projectRoleDtl?: IProjectRoleDtl;

  constructor(
    protected projectRoleDtlService: ProjectRoleDtlService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectRoleDtlService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectRoleDtlListModification');
      this.activeModal.close();
    });
  }
}
