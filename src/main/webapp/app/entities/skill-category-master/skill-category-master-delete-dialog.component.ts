import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISkillCategoryMaster } from 'app/shared/model/skill-category-master.model';
import { SkillCategoryMasterService } from './skill-category-master.service';

@Component({
  templateUrl: './skill-category-master-delete-dialog.component.html',
})
export class SkillCategoryMasterDeleteDialogComponent {
  skillCategoryMaster?: ISkillCategoryMaster;

  constructor(
    protected skillCategoryMasterService: SkillCategoryMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.skillCategoryMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('skillCategoryMasterListModification');
      this.activeModal.close();
    });
  }
}
