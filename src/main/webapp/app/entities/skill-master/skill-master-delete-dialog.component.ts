import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISkillMaster } from 'app/shared/model/skill-master.model';
import { SkillMasterService } from './skill-master.service';

@Component({
  templateUrl: './skill-master-delete-dialog.component.html',
})
export class SkillMasterDeleteDialogComponent {
  skillMaster?: ISkillMaster;

  constructor(
    protected skillMasterService: SkillMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.skillMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('skillMasterListModification');
      this.activeModal.close();
    });
  }
}
