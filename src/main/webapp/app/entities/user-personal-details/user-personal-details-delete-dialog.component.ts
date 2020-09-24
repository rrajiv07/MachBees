import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserPersonalDetails } from 'app/shared/model/user-personal-details.model';
import { UserPersonalDetailsService } from './user-personal-details.service';

@Component({
  templateUrl: './user-personal-details-delete-dialog.component.html',
})
export class UserPersonalDetailsDeleteDialogComponent {
  userPersonalDetails?: IUserPersonalDetails;

  constructor(
    protected userPersonalDetailsService: UserPersonalDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userPersonalDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userPersonalDetailsListModification');
      this.activeModal.close();
    });
  }
}
