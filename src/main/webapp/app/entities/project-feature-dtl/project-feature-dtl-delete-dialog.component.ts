import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';
import { ProjectFeatureDtlService } from './project-feature-dtl.service';

@Component({
  templateUrl: './project-feature-dtl-delete-dialog.component.html',
})
export class ProjectFeatureDtlDeleteDialogComponent {
  projectFeatureDtl?: IProjectFeatureDtl;

  constructor(
    protected projectFeatureDtlService: ProjectFeatureDtlService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectFeatureDtlService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectFeatureDtlListModification');
      this.activeModal.close();
    });
  }
}
