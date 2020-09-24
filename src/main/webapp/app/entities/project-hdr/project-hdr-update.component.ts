import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectHdr, ProjectHdr } from 'app/shared/model/project-hdr.model';
import { ProjectHdrService } from './project-hdr.service';
import { IProjectTypeMaster } from 'app/shared/model/project-type-master.model';
import { ProjectTypeMasterService } from 'app/entities/project-type-master/project-type-master.service';
import { IProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';
import { ProjectSpecificationMasterService } from 'app/entities/project-specification-master/project-specification-master.service';
import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';
import { CategoryMetadataService } from 'app/entities/category-metadata/category-metadata.service';
import { IProjectCategoryMaster } from 'app/shared/model/project-category-master.model';
import { ProjectCategoryMasterService } from 'app/entities/project-category-master/project-category-master.service';

type SelectableEntity = IProjectTypeMaster | IProjectSpecificationMaster | ICategoryMetadata | IProjectCategoryMaster;

@Component({
  selector: 'jhi-project-hdr-update',
  templateUrl: './project-hdr-update.component.html',
})
export class ProjectHdrUpdateComponent implements OnInit {
  isSaving = false;
  projecttypemasters: IProjectTypeMaster[] = [];
  projectspecificationmasters: IProjectSpecificationMaster[] = [];
  categorymetadata: ICategoryMetadata[] = [];
  projectcategorymasters: IProjectCategoryMaster[] = [];
  endDateDp: any;
  createdDateDp: any;
  modifiedDateDp: any;

  editForm = this.fb.group({
    id: [],
    overview: [null, [Validators.required, Validators.maxLength(80)]],
    projectDescription: [null, [Validators.required, Validators.maxLength(125)]],
    endDate: [],
    createdBy: [null, [Validators.required, Validators.maxLength(40)]],
    createdDate: [null, [Validators.required]],
    modifiedBy: [null, [Validators.maxLength(40)]],
    modifiedDate: [],
    projectType: [],
    projectSpecification: [],
    applicationType: [],
    model: [],
    projectCategory: [],
    visibility: [],
    preferContactedBy: [],
    status: [],
  });

  constructor(
    protected projectHdrService: ProjectHdrService,
    protected projectTypeMasterService: ProjectTypeMasterService,
    protected projectSpecificationMasterService: ProjectSpecificationMasterService,
    protected categoryMetadataService: CategoryMetadataService,
    protected projectCategoryMasterService: ProjectCategoryMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectHdr }) => {
      this.updateForm(projectHdr);

      this.projectTypeMasterService
        .query()
        .subscribe((res: HttpResponse<IProjectTypeMaster[]>) => (this.projecttypemasters = res.body || []));

      this.projectSpecificationMasterService
        .query()
        .subscribe((res: HttpResponse<IProjectSpecificationMaster[]>) => (this.projectspecificationmasters = res.body || []));

      this.categoryMetadataService.query().subscribe((res: HttpResponse<ICategoryMetadata[]>) => (this.categorymetadata = res.body || []));

      this.projectCategoryMasterService
        .query()
        .subscribe((res: HttpResponse<IProjectCategoryMaster[]>) => (this.projectcategorymasters = res.body || []));
    });
  }

  updateForm(projectHdr: IProjectHdr): void {
    this.editForm.patchValue({
      id: projectHdr.id,
      overview: projectHdr.overview,
      projectDescription: projectHdr.projectDescription,
      endDate: projectHdr.endDate,
      createdBy: projectHdr.createdBy,
      createdDate: projectHdr.createdDate,
      modifiedBy: projectHdr.modifiedBy,
      modifiedDate: projectHdr.modifiedDate,
      projectType: projectHdr.projectType,
      projectSpecification: projectHdr.projectSpecification,
      applicationType: projectHdr.applicationType,
      model: projectHdr.model,
      projectCategory: projectHdr.projectCategory,
      visibility: projectHdr.visibility,
      preferContactedBy: projectHdr.preferContactedBy,
      status: projectHdr.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectHdr = this.createFromForm();
    if (projectHdr.id !== undefined) {
      this.subscribeToSaveResponse(this.projectHdrService.update(projectHdr));
    } else {
      this.subscribeToSaveResponse(this.projectHdrService.create(projectHdr));
    }
  }

  private createFromForm(): IProjectHdr {
    return {
      ...new ProjectHdr(),
      id: this.editForm.get(['id'])!.value,
      overview: this.editForm.get(['overview'])!.value,
      projectDescription: this.editForm.get(['projectDescription'])!.value,
      endDate: this.editForm.get(['endDate'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      modifiedBy: this.editForm.get(['modifiedBy'])!.value,
      modifiedDate: this.editForm.get(['modifiedDate'])!.value,
      projectType: this.editForm.get(['projectType'])!.value,
      projectSpecification: this.editForm.get(['projectSpecification'])!.value,
      applicationType: this.editForm.get(['applicationType'])!.value,
      model: this.editForm.get(['model'])!.value,
      projectCategory: this.editForm.get(['projectCategory'])!.value,
      visibility: this.editForm.get(['visibility'])!.value,
      preferContactedBy: this.editForm.get(['preferContactedBy'])!.value,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectHdr>>): void {
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
