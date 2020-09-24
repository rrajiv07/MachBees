import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISkillCategoryMaster, SkillCategoryMaster } from 'app/shared/model/skill-category-master.model';
import { SkillCategoryMasterService } from './skill-category-master.service';

@Component({
  selector: 'jhi-skill-category-master-update',
  templateUrl: './skill-category-master-update.component.html',
})
export class SkillCategoryMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    categoryCode: [null, [Validators.required, Validators.maxLength(20)]],
    categoryName: [null, [Validators.required, Validators.maxLength(80)]],
    categoryDescription: [null, [Validators.required, Validators.maxLength(80)]],
  });

  constructor(
    protected skillCategoryMasterService: SkillCategoryMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ skillCategoryMaster }) => {
      this.updateForm(skillCategoryMaster);
    });
  }

  updateForm(skillCategoryMaster: ISkillCategoryMaster): void {
    this.editForm.patchValue({
      id: skillCategoryMaster.id,
      categoryCode: skillCategoryMaster.categoryCode,
      categoryName: skillCategoryMaster.categoryName,
      categoryDescription: skillCategoryMaster.categoryDescription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const skillCategoryMaster = this.createFromForm();
    if (skillCategoryMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.skillCategoryMasterService.update(skillCategoryMaster));
    } else {
      this.subscribeToSaveResponse(this.skillCategoryMasterService.create(skillCategoryMaster));
    }
  }

  private createFromForm(): ISkillCategoryMaster {
    return {
      ...new SkillCategoryMaster(),
      id: this.editForm.get(['id'])!.value,
      categoryCode: this.editForm.get(['categoryCode'])!.value,
      categoryName: this.editForm.get(['categoryName'])!.value,
      categoryDescription: this.editForm.get(['categoryDescription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISkillCategoryMaster>>): void {
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
