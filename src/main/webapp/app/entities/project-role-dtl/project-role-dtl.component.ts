import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';
import { ProjectRoleDtlService } from './project-role-dtl.service';
import { ProjectRoleDtlDeleteDialogComponent } from './project-role-dtl-delete-dialog.component';

@Component({
  selector: 'jhi-project-role-dtl',
  templateUrl: './project-role-dtl.component.html',
})
export class ProjectRoleDtlComponent implements OnInit, OnDestroy {
  projectRoleDtls?: IProjectRoleDtl[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectRoleDtlService: ProjectRoleDtlService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectRoleDtlService.query().subscribe((res: HttpResponse<IProjectRoleDtl[]>) => (this.projectRoleDtls = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectRoleDtls();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectRoleDtl): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectRoleDtls(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectRoleDtlListModification', () => this.loadAll());
  }

  delete(projectRoleDtl: IProjectRoleDtl): void {
    const modalRef = this.modalService.open(ProjectRoleDtlDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectRoleDtl = projectRoleDtl;
  }
}
