import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserMaster } from 'app/shared/model/user-master.model';
import { UserMasterService } from './user-master.service';

@Component({
  templateUrl: './user-master-delete-dialog.component.html',
})
export class UserMasterDeleteDialogComponent {
  userMaster?: IUserMaster;

  constructor(
    protected userMasterService: UserMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userMasterListModification');
      this.activeModal.close();
    });
  }
}
