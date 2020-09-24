import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserCompanyDetails } from 'app/shared/model/user-company-details.model';
import { UserCompanyDetailsService } from './user-company-details.service';

@Component({
  templateUrl: './user-company-details-delete-dialog.component.html',
})
export class UserCompanyDetailsDeleteDialogComponent {
  userCompanyDetails?: IUserCompanyDetails;

  constructor(
    protected userCompanyDetailsService: UserCompanyDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userCompanyDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userCompanyDetailsListModification');
      this.activeModal.close();
    });
  }
}
