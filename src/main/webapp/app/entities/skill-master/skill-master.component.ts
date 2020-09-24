import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISkillMaster } from 'app/shared/model/skill-master.model';
import { SkillMasterService } from './skill-master.service';
import { SkillMasterDeleteDialogComponent } from './skill-master-delete-dialog.component';

@Component({
  selector: 'jhi-skill-master',
  templateUrl: './skill-master.component.html',
})
export class SkillMasterComponent implements OnInit, OnDestroy {
  skillMasters?: ISkillMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected skillMasterService: SkillMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.skillMasterService.query().subscribe((res: HttpResponse<ISkillMaster[]>) => (this.skillMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSkillMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISkillMaster): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSkillMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('skillMasterListModification', () => this.loadAll());
  }

  delete(skillMaster: ISkillMaster): void {
    const modalRef = this.modalService.open(SkillMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.skillMaster = skillMaster;
  }
}
