import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectTypeMaster } from 'app/shared/model/project-type-master.model';
import { ProjectTypeMasterService } from './project-type-master.service';
import { ProjectTypeMasterDeleteDialogComponent } from './project-type-master-delete-dialog.component';

@Component({
  selector: 'jhi-project-type-master',
  templateUrl: './project-type-master.component.html',
})
export class ProjectTypeMasterComponent implements OnInit, OnDestroy {
  projectTypeMasters?: IProjectTypeMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectTypeMasterService: ProjectTypeMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectTypeMasterService
      .query()
      .subscribe((res: HttpResponse<IProjectTypeMaster[]>) => (this.projectTypeMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectTypeMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectTypeMaster): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectTypeMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectTypeMasterListModification', () => this.loadAll());
  }

  delete(projectTypeMaster: IProjectTypeMaster): void {
    const modalRef = this.modalService.open(ProjectTypeMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectTypeMaster = projectTypeMaster;
  }
}
