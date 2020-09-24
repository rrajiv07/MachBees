import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IProjectBudgetDtl, ProjectBudgetDtl } from 'app/shared/model/project-budget-dtl.model';
import { ProjectBudgetDtlService } from './project-budget-dtl.service';
import { IProjectHdr } from 'app/shared/model/project-hdr.model';
import { ProjectHdrService } from 'app/entities/project-hdr/project-hdr.service';

@Component({
  selector: 'jhi-project-budget-dtl-update',
  templateUrl: './project-budget-dtl-update.component.html',
})
export class ProjectBudgetDtlUpdateComponent implements OnInit {
  isSaving = false;
  projects: IProjectHdr[] = [];

  editForm = this.fb.group({
    id: [],
    budget: [],
    ipOwnership: [],
    project: [],
  });

  constructor(
    protected projectBudgetDtlService: ProjectBudgetDtlService,
    protected projectHdrService: ProjectHdrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectBudgetDtl }) => {
      this.updateForm(projectBudgetDtl);

      this.projectHdrService
        .query({ filter: 'projectbudgetdtl-is-null' })
        .pipe(
          map((res: HttpResponse<IProjectHdr[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProjectHdr[]) => {
          if (!projectBudgetDtl.project || !projectBudgetDtl.project.id) {
            this.projects = resBody;
          } else {
            this.projectHdrService
              .find(projectBudgetDtl.project.id)
              .pipe(
                map((subRes: HttpResponse<IProjectHdr>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProjectHdr[]) => (this.projects = concatRes));
          }
        });
    });
  }

  updateForm(projectBudgetDtl: IProjectBudgetDtl): void {
    this.editForm.patchValue({
      id: projectBudgetDtl.id,
      budget: projectBudgetDtl.budget,
      ipOwnership: projectBudgetDtl.ipOwnership,
      project: projectBudgetDtl.project,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectBudgetDtl = this.createFromForm();
    if (projectBudgetDtl.id !== undefined) {
      this.subscribeToSaveResponse(this.projectBudgetDtlService.update(projectBudgetDtl));
    } else {
      this.subscribeToSaveResponse(this.projectBudgetDtlService.create(projectBudgetDtl));
    }
  }

  private createFromForm(): IProjectBudgetDtl {
    return {
      ...new ProjectBudgetDtl(),
      id: this.editForm.get(['id'])!.value,
      budget: this.editForm.get(['budget'])!.value,
      ipOwnership: this.editForm.get(['ipOwnership'])!.value,
      project: this.editForm.get(['project'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectBudgetDtl>>): void {
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

  trackById(index: number, item: IProjectHdr): any {
    return item.id;
  }
}
