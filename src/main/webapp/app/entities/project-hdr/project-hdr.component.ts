import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectHdr } from 'app/shared/model/project-hdr.model';
import { ProjectHdrService } from './project-hdr.service';
import { ProjectHdrDeleteDialogComponent } from './project-hdr-delete-dialog.component';

@Component({
  selector: 'jhi-project-hdr',
  templateUrl: './project-hdr.component.html',
})
export class ProjectHdrComponent implements OnInit, OnDestroy {
  projectHdrs?: IProjectHdr[];
  eventSubscriber?: Subscription;

  constructor(protected projectHdrService: ProjectHdrService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.projectHdrService.query().subscribe((res: HttpResponse<IProjectHdr[]>) => (this.projectHdrs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectHdrs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectHdr): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectHdrs(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectHdrListModification', () => this.loadAll());
  }

  delete(projectHdr: IProjectHdr): void {
    const modalRef = this.modalService.open(ProjectHdrDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectHdr = projectHdr;
  }
}
