import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserLanguageDetails } from 'app/shared/model/user-language-details.model';
import { UserLanguageDetailsService } from './user-language-details.service';

@Component({
  templateUrl: './user-language-details-delete-dialog.component.html',
})
export class UserLanguageDetailsDeleteDialogComponent {
  userLanguageDetails?: IUserLanguageDetails;

  constructor(
    protected userLanguageDetailsService: UserLanguageDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userLanguageDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userLanguageDetailsListModification');
      this.activeModal.close();
    });
  }
}
