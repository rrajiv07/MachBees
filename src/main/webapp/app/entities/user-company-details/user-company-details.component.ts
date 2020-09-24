import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserCompanyDetails } from 'app/shared/model/user-company-details.model';
import { UserCompanyDetailsService } from './user-company-details.service';
import { UserCompanyDetailsDeleteDialogComponent } from './user-company-details-delete-dialog.component';

@Component({
  selector: 'jhi-user-company-details',
  templateUrl: './user-company-details.component.html',
})
export class UserCompanyDetailsComponent implements OnInit, OnDestroy {
  userCompanyDetails?: IUserCompanyDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected userCompanyDetailsService: UserCompanyDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.userCompanyDetailsService
      .query()
      .subscribe((res: HttpResponse<IUserCompanyDetails[]>) => (this.userCompanyDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserCompanyDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserCompanyDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserCompanyDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('userCompanyDetailsListModification', () => this.loadAll());
  }

  delete(userCompanyDetails: IUserCompanyDetails): void {
    const modalRef = this.modalService.open(UserCompanyDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userCompanyDetails = userCompanyDetails;
  }
}
