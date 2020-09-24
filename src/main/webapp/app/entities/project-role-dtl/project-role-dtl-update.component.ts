import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectRoleDtl, ProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';
import { ProjectRoleDtlService } from './project-role-dtl.service';
import { IProjectRoleMaster } from 'app/shared/model/project-role-master.model';
import { ProjectRoleMasterService } from 'app/entities/project-role-master/project-role-master.service';
import { IProjectHdr } from 'app/shared/model/project-hdr.model';
import { ProjectHdrService } from 'app/entities/project-hdr/project-hdr.service';

type SelectableEntity = IProjectRoleMaster | IProjectHdr;

@Component({
  selector: 'jhi-project-role-dtl-update',
  templateUrl: './project-role-dtl-update.component.html',
})
export class ProjectRoleDtlUpdateComponent implements OnInit {
  isSaving = false;
  projectrolemasters: IProjectRoleMaster[] = [];
  projecthdrs: IProjectHdr[] = [];

  editForm = this.fb.group({
    id: [],
    role: [],
    project: [],
  });

  constructor(
    protected projectRoleDtlService: ProjectRoleDtlService,
    protected projectRoleMasterService: ProjectRoleMasterService,
    protected projectHdrService: ProjectHdrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectRoleDtl }) => {
      this.updateForm(projectRoleDtl);

      this.projectRoleMasterService
        .query()
        .subscribe((res: HttpResponse<IProjectRoleMaster[]>) => (this.projectrolemasters = res.body || []));

      this.projectHdrService.query().subscribe((res: HttpResponse<IProjectHdr[]>) => (this.projecthdrs = res.body || []));
    });
  }

  updateForm(projectRoleDtl: IProjectRoleDtl): void {
    this.editForm.patchValue({
      id: projectRoleDtl.id,
      role: projectRoleDtl.role,
      project: projectRoleDtl.project,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectRoleDtl = this.createFromForm();
    if (projectRoleDtl.id !== undefined) {
      this.subscribeToSaveResponse(this.projectRoleDtlService.update(projectRoleDtl));
    } else {
      this.subscribeToSaveResponse(this.projectRoleDtlService.create(projectRoleDtl));
    }
  }

  private createFromForm(): IProjectRoleDtl {
    return {
      ...new ProjectRoleDtl(),
      id: this.editForm.get(['id'])!.value,
      role: this.editForm.get(['role'])!.value,
      project: this.editForm.get(['project'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectRoleDtl>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
