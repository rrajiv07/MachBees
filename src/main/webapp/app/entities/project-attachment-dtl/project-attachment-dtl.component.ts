import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';
import { ProjectAttachmentDtlService } from './project-attachment-dtl.service';
import { ProjectAttachmentDtlDeleteDialogComponent } from './project-attachment-dtl-delete-dialog.component';

@Component({
  selector: 'jhi-project-attachment-dtl',
  templateUrl: './project-attachment-dtl.component.html',
})
export class ProjectAttachmentDtlComponent implements OnInit, OnDestroy {
  projectAttachmentDtls?: IProjectAttachmentDtl[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectAttachmentDtlService: ProjectAttachmentDtlService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectAttachmentDtlService
      .query()
      .subscribe((res: HttpResponse<IProjectAttachmentDtl[]>) => (this.projectAttachmentDtls = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectAttachmentDtls();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectAttachmentDtl): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectAttachmentDtls(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectAttachmentDtlListModification', () => this.loadAll());
  }

  delete(projectAttachmentDtl: IProjectAttachmentDtl): void {
    const modalRef = this.modalService.open(ProjectAttachmentDtlDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectAttachmentDtl = projectAttachmentDtl;
  }
}
