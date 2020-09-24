import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserMaster } from 'app/shared/model/user-master.model';
import { UserMasterService } from './user-master.service';
import { UserMasterDeleteDialogComponent } from './user-master-delete-dialog.component';

@Component({
  selector: 'jhi-user-master',
  templateUrl: './user-master.component.html',
})
export class UserMasterComponent implements OnInit, OnDestroy {
  userMasters?: IUserMaster[];
  eventSubscriber?: Subscription;

  constructor(protected userMasterService: UserMasterService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.userMasterService.query().subscribe((res: HttpResponse<IUserMaster[]>) => (this.userMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserMaster): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('userMasterListModification', () => this.loadAll());
  }

  delete(userMaster: IUserMaster): void {
    const modalRef = this.modalService.open(UserMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userMaster = userMaster;
  }
}
