import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectFeatureDtl, ProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';
import { ProjectFeatureDtlService } from './project-feature-dtl.service';
import { IProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';
import { ProjectFeatureMasterService } from 'app/entities/project-feature-master/project-feature-master.service';
import { IProjectHdr } from 'app/shared/model/project-hdr.model';
import { ProjectHdrService } from 'app/entities/project-hdr/project-hdr.service';

type SelectableEntity = IProjectFeatureMaster | IProjectHdr;

@Component({
  selector: 'jhi-project-feature-dtl-update',
  templateUrl: './project-feature-dtl-update.component.html',
})
export class ProjectFeatureDtlUpdateComponent implements OnInit {
  isSaving = false;
  projectfeaturemasters: IProjectFeatureMaster[] = [];
  projecthdrs: IProjectHdr[] = [];

  editForm = this.fb.group({
    id: [],
    feature: [],
    project: [],
  });

  constructor(
    protected projectFeatureDtlService: ProjectFeatureDtlService,
    protected projectFeatureMasterService: ProjectFeatureMasterService,
    protected projectHdrService: ProjectHdrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectFeatureDtl }) => {
      this.updateForm(projectFeatureDtl);

      this.projectFeatureMasterService
        .query()
        .subscribe((res: HttpResponse<IProjectFeatureMaster[]>) => (this.projectfeaturemasters = res.body || []));

      this.projectHdrService.query().subscribe((res: HttpResponse<IProjectHdr[]>) => (this.projecthdrs = res.body || []));
    });
  }

  updateForm(projectFeatureDtl: IProjectFeatureDtl): void {
    this.editForm.patchValue({
      id: projectFeatureDtl.id,
      feature: projectFeatureDtl.feature,
      project: projectFeatureDtl.project,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectFeatureDtl = this.createFromForm();
    if (projectFeatureDtl.id !== undefined) {
      this.subscribeToSaveResponse(this.projectFeatureDtlService.update(projectFeatureDtl));
    } else {
      this.subscribeToSaveResponse(this.projectFeatureDtlService.create(projectFeatureDtl));
    }
  }

  private createFromForm(): IProjectFeatureDtl {
    return {
      ...new ProjectFeatureDtl(),
      id: this.editForm.get(['id'])!.value,
      feature: this.editForm.get(['feature'])!.value,
      project: this.editForm.get(['project'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectFeatureDtl>>): void {
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
