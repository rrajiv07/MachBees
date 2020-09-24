import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserLanguageDetails, UserLanguageDetails } from 'app/shared/model/user-language-details.model';
import { UserLanguageDetailsService } from './user-language-details.service';
import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';
import { CategoryMetadataService } from 'app/entities/category-metadata/category-metadata.service';
import { IUserMaster } from 'app/shared/model/user-master.model';
import { UserMasterService } from 'app/entities/user-master/user-master.service';

type SelectableEntity = ICategoryMetadata | IUserMaster;

@Component({
  selector: 'jhi-user-language-details-update',
  templateUrl: './user-language-details-update.component.html',
})
export class UserLanguageDetailsUpdateComponent implements OnInit {
  isSaving = false;
  categorymetadata: ICategoryMetadata[] = [];
  usermasters: IUserMaster[] = [];

  editForm = this.fb.group({
    id: [],
    language: [],
    proficiency: [],
    user: [],
  });

  constructor(
    protected userLanguageDetailsService: UserLanguageDetailsService,
    protected categoryMetadataService: CategoryMetadataService,
    protected userMasterService: UserMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userLanguageDetails }) => {
      this.updateForm(userLanguageDetails);

      this.categoryMetadataService.query().subscribe((res: HttpResponse<ICategoryMetadata[]>) => (this.categorymetadata = res.body || []));

      this.userMasterService.query().subscribe((res: HttpResponse<IUserMaster[]>) => (this.usermasters = res.body || []));
    });
  }

  updateForm(userLanguageDetails: IUserLanguageDetails): void {
    this.editForm.patchValue({
      id: userLanguageDetails.id,
      language: userLanguageDetails.language,
      proficiency: userLanguageDetails.proficiency,
      user: userLanguageDetails.user,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userLanguageDetails = this.createFromForm();
    if (userLanguageDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.userLanguageDetailsService.update(userLanguageDetails));
    } else {
      this.subscribeToSaveResponse(this.userLanguageDetailsService.create(userLanguageDetails));
    }
  }

  private createFromForm(): IUserLanguageDetails {
    return {
      ...new UserLanguageDetails(),
      id: this.editForm.get(['id'])!.value,
      language: this.editForm.get(['language'])!.value,
      proficiency: this.editForm.get(['proficiency'])!.value,
      user: this.editForm.get(['user'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserLanguageDetails>>): void {
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
