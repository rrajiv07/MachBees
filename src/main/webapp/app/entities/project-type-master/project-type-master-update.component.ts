import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectTypeMaster, ProjectTypeMaster } from 'app/shared/model/project-type-master.model';
import { ProjectTypeMasterService } from './project-type-master.service';

@Component({
  selector: 'jhi-project-type-master-update',
  templateUrl: './project-type-master-update.component.html',
})
export class ProjectTypeMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    projectTypeCode: [null, [Validators.required, Validators.maxLength(20)]],
    projectTypeName: [null, [Validators.required, Validators.maxLength(80)]],
    projectTypeDescription: [null, [Validators.required, Validators.maxLength(80)]],
  });

  constructor(
    protected projectTypeMasterService: ProjectTypeMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectTypeMaster }) => {
      this.updateForm(projectTypeMaster);
    });
  }

  updateForm(projectTypeMaster: IProjectTypeMaster): void {
    this.editForm.patchValue({
      id: projectTypeMaster.id,
      projectTypeCode: projectTypeMaster.projectTypeCode,
      projectTypeName: projectTypeMaster.projectTypeName,
      projectTypeDescription: projectTypeMaster.projectTypeDescription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectTypeMaster = this.createFromForm();
    if (projectTypeMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.projectTypeMasterService.update(projectTypeMaster));
    } else {
      this.subscribeToSaveResponse(this.projectTypeMasterService.create(projectTypeMaster));
    }
  }

  private createFromForm(): IProjectTypeMaster {
    return {
      ...new ProjectTypeMaster(),
      id: this.editForm.get(['id'])!.value,
      projectTypeCode: this.editForm.get(['projectTypeCode'])!.value,
      projectTypeName: this.editForm.get(['projectTypeName'])!.value,
      projectTypeDescription: this.editForm.get(['projectTypeDescription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectTypeMaster>>): void {
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
