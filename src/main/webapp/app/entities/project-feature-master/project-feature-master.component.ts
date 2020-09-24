import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';
import { ProjectFeatureMasterService } from './project-feature-master.service';
import { ProjectFeatureMasterDeleteDialogComponent } from './project-feature-master-delete-dialog.component';

@Component({
  selector: 'jhi-project-feature-master',
  templateUrl: './project-feature-master.component.html',
})
export class ProjectFeatureMasterComponent implements OnInit, OnDestroy {
  projectFeatureMasters?: IProjectFeatureMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectFeatureMasterService: ProjectFeatureMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectFeatureMasterService
      .query()
      .subscribe((res: HttpResponse<IProjectFeatureMaster[]>) => (this.projectFeatureMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectFeatureMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectFeatureMaster): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectFeatureMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectFeatureMasterListModification', () => this.loadAll());
  }

  delete(projectFeatureMaster: IProjectFeatureMaster): void {
    const modalRef = this.modalService.open(ProjectFeatureMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectFeatureMaster = projectFeatureMaster;
  }
}
