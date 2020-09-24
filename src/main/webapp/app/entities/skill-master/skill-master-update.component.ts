import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISkillMaster, SkillMaster } from 'app/shared/model/skill-master.model';
import { SkillMasterService } from './skill-master.service';
import { ISkillCategoryMaster } from 'app/shared/model/skill-category-master.model';
import { SkillCategoryMasterService } from 'app/entities/skill-category-master/skill-category-master.service';

@Component({
  selector: 'jhi-skill-master-update',
  templateUrl: './skill-master-update.component.html',
})
export class SkillMasterUpdateComponent implements OnInit {
  isSaving = false;
  skillcategorymasters: ISkillCategoryMaster[] = [];

  editForm = this.fb.group({
    id: [],
    skillCode: [null, [Validators.required, Validators.maxLength(40)]],
    skillName: [null, [Validators.required, Validators.maxLength(80)]],
    skillDescription: [null, [Validators.required, Validators.maxLength(80)]],
    skillCategory: [],
  });

  constructor(
    protected skillMasterService: SkillMasterService,
    protected skillCategoryMasterService: SkillCategoryMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ skillMaster }) => {
      this.updateForm(skillMaster);

      this.skillCategoryMasterService
        .query()
        .subscribe((res: HttpResponse<ISkillCategoryMaster[]>) => (this.skillcategorymasters = res.body || []));
    });
  }

  updateForm(skillMaster: ISkillMaster): void {
    this.editForm.patchValue({
      id: skillMaster.id,
      skillCode: skillMaster.skillCode,
      skillName: skillMaster.skillName,
      skillDescription: skillMaster.skillDescription,
      skillCategory: skillMaster.skillCategory,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const skillMaster = this.createFromForm();
    if (skillMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.skillMasterService.update(skillMaster));
    } else {
      this.subscribeToSaveResponse(this.skillMasterService.create(skillMaster));
    }
  }

  private createFromForm(): ISkillMaster {
    return {
      ...new SkillMaster(),
      id: this.editForm.get(['id'])!.value,
      skillCode: this.editForm.get(['skillCode'])!.value,
      skillName: this.editForm.get(['skillName'])!.value,
      skillDescription: this.editForm.get(['skillDescription'])!.value,
      skillCategory: this.editForm.get(['skillCategory'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISkillMaster>>): void {
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

  trackById(index: number, item: ISkillCategoryMaster): any {
    return item.id;
  }
}
