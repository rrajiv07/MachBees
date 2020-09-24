import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProfileMaster } from 'app/shared/model/profile-master.model';
import { ProfileMasterService } from './profile-master.service';
import { ProfileMasterDeleteDialogComponent } from './profile-master-delete-dialog.component';

@Component({
  selector: 'jhi-profile-master',
  templateUrl: './profile-master.component.html',
})
export class ProfileMasterComponent implements OnInit, OnDestroy {
  profileMasters?: IProfileMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected profileMasterService: ProfileMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.profileMasterService.query().subscribe((res: HttpResponse<IProfileMaster[]>) => (this.profileMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProfileMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProfileMaster): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProfileMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('profileMasterListModification', () => this.loadAll());
  }

  delete(profileMaster: IProfileMaster): void {
    const modalRef = this.modalService.open(ProfileMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.profileMaster = profileMaster;
  }
}
