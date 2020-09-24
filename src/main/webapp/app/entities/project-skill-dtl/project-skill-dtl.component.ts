import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';
import { ProjectSkillDtlService } from './project-skill-dtl.service';
import { ProjectSkillDtlDeleteDialogComponent } from './project-skill-dtl-delete-dialog.component';

@Component({
  selector: 'jhi-project-skill-dtl',
  templateUrl: './project-skill-dtl.component.html',
})
export class ProjectSkillDtlComponent implements OnInit, OnDestroy {
  projectSkillDtls?: IProjectSkillDtl[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectSkillDtlService: ProjectSkillDtlService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectSkillDtlService.query().subscribe((res: HttpResponse<IProjectSkillDtl[]>) => (this.projectSkillDtls = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectSkillDtls();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectSkillDtl): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectSkillDtls(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectSkillDtlListModification', () => this.loadAll());
  }

  delete(projectSkillDtl: IProjectSkillDtl): void {
    const modalRef = this.modalService.open(ProjectSkillDtlDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectSkillDtl = projectSkillDtl;
  }
}
