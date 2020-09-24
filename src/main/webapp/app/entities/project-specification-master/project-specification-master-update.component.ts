import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectSpecificationMaster, ProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';
import { ProjectSpecificationMasterService } from './project-specification-master.service';

@Component({
  selector: 'jhi-project-specification-master-update',
  templateUrl: './project-specification-master-update.component.html',
})
export class ProjectSpecificationMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    specificationCode: [null, [Validators.required, Validators.maxLength(20)]],
    specificationName: [null, [Validators.required, Validators.maxLength(100)]],
    specificationDescription: [null, [Validators.required, Validators.maxLength(100)]],
  });

  constructor(
    protected projectSpecificationMasterService: ProjectSpecificationMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectSpecificationMaster }) => {
      this.updateForm(projectSpecificationMaster);
    });
  }

  updateForm(projectSpecificationMaster: IProjectSpecificationMaster): void {
    this.editForm.patchValue({
      id: projectSpecificationMaster.id,
      specificationCode: projectSpecificationMaster.specificationCode,
      specificationName: projectSpecificationMaster.specificationName,
      specificationDescription: projectSpecificationMaster.specificationDescription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectSpecificationMaster = this.createFromForm();
    if (projectSpecificationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.projectSpecificationMasterService.update(projectSpecificationMaster));
    } else {
      this.subscribeToSaveResponse(this.projectSpecificationMasterService.create(projectSpecificationMaster));
    }
  }

  private createFromForm(): IProjectSpecificationMaster {
    return {
      ...new ProjectSpecificationMaster(),
      id: this.editForm.get(['id'])!.value,
      specificationCode: this.editForm.get(['specificationCode'])!.value,
      specificationName: this.editForm.get(['specificationName'])!.value,
      specificationDescription: this.editForm.get(['specificationDescription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectSpecificationMaster>>): void {
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
