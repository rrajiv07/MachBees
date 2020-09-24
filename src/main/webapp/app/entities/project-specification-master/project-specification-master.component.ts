import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';
import { ProjectSpecificationMasterService } from './project-specification-master.service';
import { ProjectSpecificationMasterDeleteDialogComponent } from './project-specification-master-delete-dialog.component';

@Component({
  selector: 'jhi-project-specification-master',
  templateUrl: './project-specification-master.component.html',
})
export class ProjectSpecificationMasterComponent implements OnInit, OnDestroy {
  projectSpecificationMasters?: IProjectSpecificationMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectSpecificationMasterService: ProjectSpecificationMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectSpecificationMasterService
      .query()
      .subscribe((res: HttpResponse<IProjectSpecificationMaster[]>) => (this.projectSpecificationMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectSpecificationMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectSpecificationMaster): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectSpecificationMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectSpecificationMasterListModification', () => this.loadAll());
  }

  delete(projectSpecificationMaster: IProjectSpecificationMaster): void {
    const modalRef = this.modalService.open(ProjectSpecificationMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectSpecificationMaster = projectSpecificationMaster;
  }
}
