import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategoryMetadata, CategoryMetadata } from 'app/shared/model/category-metadata.model';
import { CategoryMetadataService } from './category-metadata.service';

@Component({
  selector: 'jhi-category-metadata-update',
  templateUrl: './category-metadata-update.component.html',
})
export class CategoryMetadataUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    categoryCode: [null, [Validators.required, Validators.maxLength(40)]],
    categoryName: [null, [Validators.required, Validators.maxLength(40)]],
    categoryDescription: [null, [Validators.required, Validators.maxLength(80)]],
    sequenceNumber: [null, [Validators.required]],
  });

  constructor(
    protected categoryMetadataService: CategoryMetadataService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categoryMetadata }) => {
      this.updateForm(categoryMetadata);
    });
  }

  updateForm(categoryMetadata: ICategoryMetadata): void {
    this.editForm.patchValue({
      id: categoryMetadata.id,
      categoryCode: categoryMetadata.categoryCode,
      categoryName: categoryMetadata.categoryName,
      categoryDescription: categoryMetadata.categoryDescription,
      sequenceNumber: categoryMetadata.sequenceNumber,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const categoryMetadata = this.createFromForm();
    if (categoryMetadata.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryMetadataService.update(categoryMetadata));
    } else {
      this.subscribeToSaveResponse(this.categoryMetadataService.create(categoryMetadata));
    }
  }

  private createFromForm(): ICategoryMetadata {
    return {
      ...new CategoryMetadata(),
      id: this.editForm.get(['id'])!.value,
      categoryCode: this.editForm.get(['categoryCode'])!.value,
      categoryName: this.editForm.get(['categoryName'])!.value,
      categoryDescription: this.editForm.get(['categoryDescription'])!.value,
      sequenceNumber: this.editForm.get(['sequenceNumber'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategoryMetadata>>): void {
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
