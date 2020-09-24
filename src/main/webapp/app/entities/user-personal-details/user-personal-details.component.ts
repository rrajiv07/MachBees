import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserPersonalDetails } from 'app/shared/model/user-personal-details.model';
import { UserPersonalDetailsService } from './user-personal-details.service';
import { UserPersonalDetailsDeleteDialogComponent } from './user-personal-details-delete-dialog.component';

@Component({
  selector: 'jhi-user-personal-details',
  templateUrl: './user-personal-details.component.html',
})
export class UserPersonalDetailsComponent implements OnInit, OnDestroy {
  userPersonalDetails?: IUserPersonalDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected userPersonalDetailsService: UserPersonalDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.userPersonalDetailsService
      .query()
      .subscribe((res: HttpResponse<IUserPersonalDetails[]>) => (this.userPersonalDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserPersonalDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserPersonalDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserPersonalDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('userPersonalDetailsListModification', () => this.loadAll());
  }

  delete(userPersonalDetails: IUserPersonalDetails): void {
    const modalRef = this.modalService.open(UserPersonalDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userPersonalDetails = userPersonalDetails;
  }
}
