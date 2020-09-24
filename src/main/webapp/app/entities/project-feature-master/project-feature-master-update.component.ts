import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectFeatureMaster, ProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';
import { ProjectFeatureMasterService } from './project-feature-master.service';

@Component({
  selector: 'jhi-project-feature-master-update',
  templateUrl: './project-feature-master-update.component.html',
})
export class ProjectFeatureMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    featureCode: [null, [Validators.required, Validators.maxLength(40)]],
    featureName: [null, [Validators.required, Validators.maxLength(80)]],
    featureDescription: [null, [Validators.required, Validators.maxLength(80)]],
  });

  constructor(
    protected projectFeatureMasterService: ProjectFeatureMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectFeatureMaster }) => {
      this.updateForm(projectFeatureMaster);
    });
  }

  updateForm(projectFeatureMaster: IProjectFeatureMaster): void {
    this.editForm.patchValue({
      id: projectFeatureMaster.id,
      featureCode: projectFeatureMaster.featureCode,
      featureName: projectFeatureMaster.featureName,
      featureDescription: projectFeatureMaster.featureDescription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectFeatureMaster = this.createFromForm();
    if (projectFeatureMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.projectFeatureMasterService.update(projectFeatureMaster));
    } else {
      this.subscribeToSaveResponse(this.projectFeatureMasterService.create(projectFeatureMaster));
    }
  }

  private createFromForm(): IProjectFeatureMaster {
    return {
      ...new ProjectFeatureMaster(),
      id: this.editForm.get(['id'])!.value,
      featureCode: this.editForm.get(['featureCode'])!.value,
      featureName: this.editForm.get(['featureName'])!.value,
      featureDescription: this.editForm.get(['featureDescription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectFeatureMaster>>): void {
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
}
