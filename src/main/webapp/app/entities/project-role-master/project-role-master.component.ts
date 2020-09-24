import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectRoleMaster } from 'app/shared/model/project-role-master.model';
import { ProjectRoleMasterService } from './project-role-master.service';
import { ProjectRoleMasterDeleteDialogComponent } from './project-role-master-delete-dialog.component';

@Component({
  selector: 'jhi-project-role-master',
  templateUrl: './project-role-master.component.html',
})
export class ProjectRoleMasterComponent implements OnInit, OnDestroy {
  projectRoleMasters?: IProjectRoleMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectRoleMasterService: ProjectRoleMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectRoleMasterService
      .query()
      .subscribe((res: HttpResponse<IProjectRoleMaster[]>) => (this.projectRoleMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectRoleMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectRoleMaster): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectRoleMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectRoleMasterListModification', () => this.loadAll());
  }

  delete(projectRoleMaster: IProjectRoleMaster): void {
    const modalRef = this.modalService.open(ProjectRoleMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectRoleMaster = projectRoleMaster;
  }
}
