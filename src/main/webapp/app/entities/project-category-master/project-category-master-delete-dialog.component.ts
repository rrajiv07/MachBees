import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectCategoryMaster } from 'app/shared/model/project-category-master.model';
import { ProjectCategoryMasterService } from './project-category-master.service';

@Component({
  templateUrl: './project-category-master-delete-dialog.component.html',
})
export class ProjectCategoryMasterDeleteDialogComponent {
  projectCategoryMaster?: IProjectCategoryMaster;

  constructor(
    protected projectCategoryMasterService: ProjectCategoryMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectCategoryMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectCategoryMasterListModification');
      this.activeModal.close();
    });
  }
}
