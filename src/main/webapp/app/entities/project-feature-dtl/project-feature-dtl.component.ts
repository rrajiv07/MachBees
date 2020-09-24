import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';
import { ProjectFeatureDtlService } from './project-feature-dtl.service';
import { ProjectFeatureDtlDeleteDialogComponent } from './project-feature-dtl-delete-dialog.component';

@Component({
  selector: 'jhi-project-feature-dtl',
  templateUrl: './project-feature-dtl.component.html',
})
export class ProjectFeatureDtlComponent implements OnInit, OnDestroy {
  projectFeatureDtls?: IProjectFeatureDtl[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectFeatureDtlService: ProjectFeatureDtlService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectFeatureDtlService
      .query()
      .subscribe((res: HttpResponse<IProjectFeatureDtl[]>) => (this.projectFeatureDtls = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectFeatureDtls();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectFeatureDtl): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectFeatureDtls(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectFeatureDtlListModification', () => this.loadAll());
  }

  delete(projectFeatureDtl: IProjectFeatureDtl): void {
    const modalRef = this.modalService.open(ProjectFeatureDtlDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectFeatureDtl = projectFeatureDtl;
  }
}
