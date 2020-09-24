import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISkillCategoryMaster } from 'app/shared/model/skill-category-master.model';
import { SkillCategoryMasterService } from './skill-category-master.service';
import { SkillCategoryMasterDeleteDialogComponent } from './skill-category-master-delete-dialog.component';

@Component({
  selector: 'jhi-skill-category-master',
  templateUrl: './skill-category-master.component.html',
})
export class SkillCategoryMasterComponent implements OnInit, OnDestroy {
  skillCategoryMasters?: ISkillCategoryMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected skillCategoryMasterService: SkillCategoryMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.skillCategoryMasterService
      .query()
      .subscribe((res: HttpResponse<ISkillCategoryMaster[]>) => (this.skillCategoryMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSkillCategoryMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISkillCategoryMaster): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSkillCategoryMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('skillCategoryMasterListModification', () => this.loadAll());
  }

  delete(skillCategoryMaster: ISkillCategoryMaster): void {
    const modalRef = this.modalService.open(SkillCategoryMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.skillCategoryMaster = skillCategoryMaster;
  }
}
