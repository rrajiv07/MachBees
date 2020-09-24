import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectAttachmentDtl, ProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';
import { ProjectAttachmentDtlService } from './project-attachment-dtl.service';
import { IProjectHdr } from 'app/shared/model/project-hdr.model';
import { ProjectHdrService } from 'app/entities/project-hdr/project-hdr.service';

@Component({
  selector: 'jhi-project-attachment-dtl-update',
  templateUrl: './project-attachment-dtl-update.component.html',
})
export class ProjectAttachmentDtlUpdateComponent implements OnInit {
  isSaving = false;
  projecthdrs: IProjectHdr[] = [];

  editForm = this.fb.group({
    id: [],
    fileId: [null, [Validators.required, Validators.maxLength(40)]],
    fileName: [null, [Validators.required, Validators.maxLength(255)]],
    fileType: [null, [Validators.required, Validators.maxLength(10)]],
    project: [],
  });

  constructor(
    protected projectAttachmentDtlService: ProjectAttachmentDtlService,
    protected projectHdrService: ProjectHdrService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectAttachmentDtl }) => {
      this.updateForm(projectAttachmentDtl);

      this.projectHdrService.query().subscribe((res: HttpResponse<IProjectHdr[]>) => (this.projecthdrs = res.body || []));
    });
  }

  updateForm(projectAttachmentDtl: IProjectAttachmentDtl): void {
    this.editForm.patchValue({
      id: projectAttachmentDtl.id,
      fileId: projectAttachmentDtl.fileId,
      fileName: projectAttachmentDtl.fileName,
      fileType: projectAttachmentDtl.fileType,
      project: projectAttachmentDtl.project,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectAttachmentDtl = this.createFromForm();
    if (projectAttachmentDtl.id !== undefined) {
      this.subscribeToSaveResponse(this.projectAttachmentDtlService.update(projectAttachmentDtl));
    } else {
      this.subscribeToSaveResponse(this.projectAttachmentDtlService.create(projectAttachmentDtl));
    }
  }

  private createFromForm(): IProjectAttachmentDtl {
    return {
      ...new ProjectAttachmentDtl(),
      id: this.editForm.get(['id'])!.value,
      fileId: this.editForm.get(['fileId'])!.value,
      fileName: this.editForm.get(['fileName'])!.value,
      fileType: this.editForm.get(['fileType'])!.value,
      project: this.editForm.get(['project'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectAttachmentDtl>>): void {
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
