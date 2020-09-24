import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectRoleMaster, ProjectRoleMaster } from 'app/shared/model/project-role-master.model';
import { ProjectRoleMasterService } from './project-role-master.service';

@Component({
  selector: 'jhi-project-role-master-update',
  templateUrl: './project-role-master-update.component.html',
})
export class ProjectRoleMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    roleCode: [null, [Validators.required, Validators.maxLength(20)]],
    roleName: [null, [Validators.required, Validators.maxLength(80)]],
    roleDescription: [null, [Validators.required, Validators.maxLength(80)]],
  });

  constructor(
    protected projectRoleMasterService: ProjectRoleMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectRoleMaster }) => {
      this.updateForm(projectRoleMaster);
    });
  }

  updateForm(projectRoleMaster: IProjectRoleMaster): void {
    this.editForm.patchValue({
      id: projectRoleMaster.id,
      roleCode: projectRoleMaster.roleCode,
      roleName: projectRoleMaster.roleName,
      roleDescription: projectRoleMaster.roleDescription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectRoleMaster = this.createFromForm();
    if (projectRoleMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.projectRoleMasterService.update(projectRoleMaster));
    } else {
      this.subscribeToSaveResponse(this.projectRoleMasterService.create(projectRoleMaster));
    }
  }

  private createFromForm(): IProjectRoleMaster {
    return {
      ...new ProjectRoleMaster(),
      id: this.editForm.get(['id'])!.value,
      roleCode: this.editForm.get(['roleCode'])!.value,
      roleName: this.editForm.get(['roleName'])!.value,
      roleDescription: this.editForm.get(['roleDescription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectRoleMaster>>): void {
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
