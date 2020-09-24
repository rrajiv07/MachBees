import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectBudgetDtl } from 'app/shared/model/project-budget-dtl.model';
import { ProjectBudgetDtlService } from './project-budget-dtl.service';
import { ProjectBudgetDtlDeleteDialogComponent } from './project-budget-dtl-delete-dialog.component';

@Component({
  selector: 'jhi-project-budget-dtl',
  templateUrl: './project-budget-dtl.component.html',
})
export class ProjectBudgetDtlComponent implements OnInit, OnDestroy {
  projectBudgetDtls?: IProjectBudgetDtl[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectBudgetDtlService: ProjectBudgetDtlService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectBudgetDtlService.query().subscribe((res: HttpResponse<IProjectBudgetDtl[]>) => (this.projectBudgetDtls = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectBudgetDtls();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectBudgetDtl): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectBudgetDtls(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectBudgetDtlListModification', () => this.loadAll());
  }

  delete(projectBudgetDtl: IProjectBudgetDtl): void {
    const modalRef = this.modalService.open(ProjectBudgetDtlDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectBudgetDtl = projectBudgetDtl;
  }
}
