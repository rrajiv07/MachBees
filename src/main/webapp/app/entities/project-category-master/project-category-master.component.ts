import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectCategoryMaster } from 'app/shared/model/project-category-master.model';
import { ProjectCategoryMasterService } from './project-category-master.service';
import { ProjectCategoryMasterDeleteDialogComponent } from './project-category-master-delete-dialog.component';

@Component({
  selector: 'jhi-project-category-master',
  templateUrl: './project-category-master.component.html',
})
export class ProjectCategoryMasterComponent implements OnInit, OnDestroy {
  projectCategoryMasters?: IProjectCategoryMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectCategoryMasterService: ProjectCategoryMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectCategoryMasterService
      .query()
      .subscribe((res: HttpResponse<IProjectCategoryMaster[]>) => (this.projectCategoryMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectCategoryMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectCategoryMaster): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectCategoryMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectCategoryMasterListModification', () => this.loadAll());
  }

  delete(projectCategoryMaster: IProjectCategoryMaster): void {
    const modalRef = this.modalService.open(ProjectCategoryMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectCategoryMaster = projectCategoryMaster;
  }
}
