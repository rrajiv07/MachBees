import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectSkillDtl, ProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';
import { ProjectSkillDtlService } from './project-skill-dtl.service';
import { ISkillMaster } from 'app/shared/model/skill-master.model';
import { SkillMasterService } from 'app/entities/skill-master/skill-master.service';
import { IProjectHdr } from 'app/shared/model/project-hdr.model';
import { ProjectHdrService } from 'app/entities/project-hdr/project-hdr.service';

type SelectableEntity = ISkillMaster | IProjectHdr;

@Component({
  selector: 'jhi-project-skill-dtl-update',
  templateUrl: './project-skill-dtl-update.component.html',
})
export class ProjectSkillDtlUpdateComponent implements OnInit {
  isSaving = false;
  skillmasters: ISkillMaster[] = [];
  projecthdrs: IProjectHdr[] = [];

  editForm = this.fb.group({
    id: [],
    skill: [],
    project: [],
  });

  constructor(
    protected projectSkillDtlService: ProjectSkillDtlService,
    protected skillMasterService: SkillMasterService,
    protected projectHdrService: ProjectHdrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectSkillDtl }) => {
      this.updateForm(projectSkillDtl);

      this.skillMasterService.query().subscribe((res: HttpResponse<ISkillMaster[]>) => (this.skillmasters = res.body || []));

      this.projectHdrService.query().subscribe((res: HttpResponse<IProjectHdr[]>) => (this.projecthdrs = res.body || []));
    });
  }

  updateForm(projectSkillDtl: IProjectSkillDtl): void {
    this.editForm.patchValue({
      id: projectSkillDtl.id,
      skill: projectSkillDtl.skill,
      project: projectSkillDtl.project,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectSkillDtl = this.createFromForm();
    if (projectSkillDtl.id !== undefined) {
      this.subscribeToSaveResponse(this.projectSkillDtlService.update(projectSkillDtl));
    } else {
      this.subscribeToSaveResponse(this.projectSkillDtlService.create(projectSkillDtl));
    }
  }

  private createFromForm(): IProjectSkillDtl {
    return {
      ...new ProjectSkillDtl(),
      id: this.editForm.get(['id'])!.value,
      skill: this.editForm.get(['skill'])!.value,
      project: this.editForm.get(['project'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectSkillDtl>>): void {
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
