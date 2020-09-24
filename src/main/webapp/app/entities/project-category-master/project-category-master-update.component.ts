import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectCategoryMaster, ProjectCategoryMaster } from 'app/shared/model/project-category-master.model';
import { ProjectCategoryMasterService } from './project-category-master.service';

@Component({
  selector: 'jhi-project-category-master-update',
  templateUrl: './project-category-master-update.component.html',
})
export class ProjectCategoryMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    categoryCode: [null, [Validators.required, Validators.maxLength(20)]],
    categoryName: [null, [Validators.required, Validators.maxLength(80)]],
    categoryDescription: [null, [Validators.required, Validators.maxLength(80)]],
  });

  constructor(
    protected projectCategoryMasterService: ProjectCategoryMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectCategoryMaster }) => {
      this.updateForm(projectCategoryMaster);
    });
  }

  updateForm(projectCategoryMaster: IProjectCategoryMaster): void {
    this.editForm.patchValue({
      id: projectCategoryMaster.id,
      categoryCode: projectCategoryMaster.categoryCode,
      categoryName: projectCategoryMaster.categoryName,
      categoryDescription: projectCategoryMaster.categoryDescription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectCategoryMaster = this.createFromForm();
    if (projectCategoryMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.projectCategoryMasterService.update(projectCategoryMaster));
    } else {
      this.subscribeToSaveResponse(this.projectCategoryMasterService.create(projectCategoryMaster));
    }
  }

  private createFromForm(): IProjectCategoryMaster {
    return {
      ...new ProjectCategoryMaster(),
      id: this.editForm.get(['id'])!.value,
      categoryCode: this.editForm.get(['categoryCode'])!.value,
      categoryName: this.editForm.get(['categoryName'])!.value,
      categoryDescription: this.editForm.get(['categoryDescription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectCategoryMaster>>): void {
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
