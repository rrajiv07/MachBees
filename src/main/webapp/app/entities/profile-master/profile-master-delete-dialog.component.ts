import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfileMaster } from 'app/shared/model/profile-master.model';
import { ProfileMasterService } from './profile-master.service';

@Component({
  templateUrl: './profile-master-delete-dialog.component.html',
})
export class ProfileMasterDeleteDialogComponent {
  profileMaster?: IProfileMaster;

  constructor(
    protected profileMasterService: ProfileMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.profileMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('profileMasterListModification');
      this.activeModal.close();
    });
  }
}
