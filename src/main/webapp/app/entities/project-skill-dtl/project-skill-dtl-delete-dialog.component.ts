import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';
import { ProjectSkillDtlService } from './project-skill-dtl.service';

@Component({
  templateUrl: './project-skill-dtl-delete-dialog.component.html',
})
export class ProjectSkillDtlDeleteDialogComponent {
  projectSkillDtl?: IProjectSkillDtl;

  constructor(
    protected projectSkillDtlService: ProjectSkillDtlService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectSkillDtlService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectSkillDtlListModification');
      this.activeModal.close();
    });
  }
}
