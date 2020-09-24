import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectBudgetDtl } from 'app/shared/model/project-budget-dtl.model';
import { ProjectBudgetDtlService } from './project-budget-dtl.service';

@Component({
  templateUrl: './project-budget-dtl-delete-dialog.component.html',
})
export class ProjectBudgetDtlDeleteDialogComponent {
  projectBudgetDtl?: IProjectBudgetDtl;

  constructor(
    protected projectBudgetDtlService: ProjectBudgetDtlService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectBudgetDtlService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectBudgetDtlListModification');
      this.activeModal.close();
    });
  }
}
