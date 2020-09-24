import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserLanguageDetails } from 'app/shared/model/user-language-details.model';
import { UserLanguageDetailsService } from './user-language-details.service';
import { UserLanguageDetailsDeleteDialogComponent } from './user-language-details-delete-dialog.component';

@Component({
  selector: 'jhi-user-language-details',
  templateUrl: './user-language-details.component.html',
})
export class UserLanguageDetailsComponent implements OnInit, OnDestroy {
  userLanguageDetails?: IUserLanguageDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected userLanguageDetailsService: UserLanguageDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.userLanguageDetailsService
      .query()
      .subscribe((res: HttpResponse<IUserLanguageDetails[]>) => (this.userLanguageDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserLanguageDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserLanguageDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserLanguageDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('userLanguageDetailsListModification', () => this.loadAll());
  }

  delete(userLanguageDetails: IUserLanguageDetails): void {
    const modalRef = this.modalService.open(UserLanguageDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userLanguageDetails = userLanguageDetails;
  }
}
