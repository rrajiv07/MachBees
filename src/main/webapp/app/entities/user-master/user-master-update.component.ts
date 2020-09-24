import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserMaster, UserMaster } from 'app/shared/model/user-master.model';
import { UserMasterService } from './user-master.service';
import { ICategoryMetadata } from 'app/shared/model/category-metadata.model';
import { CategoryMetadataService } from 'app/entities/category-metadata/category-metadata.service';
import { IProfileMaster } from 'app/shared/model/profile-master.model';
import { ProfileMasterService } from 'app/entities/profile-master/profile-master.service';

type SelectableEntity = ICategoryMetadata | IProfileMaster;

@Component({
  selector: 'jhi-user-master-update',
  templateUrl: './user-master-update.component.html',
})
export class UserMasterUpdateComponent implements OnInit {
  isSaving = false;
  categorymetadata: ICategoryMetadata[] = [];
  profilemasters: IProfileMaster[] = [];
  updatedDateDp: any;

  editForm = this.fb.group({
    id: [],
    emailId: [null, [Validators.required, Validators.maxLength(40), Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    password: [null, [Validators.required, Validators.maxLength(40)]],
    updatedBy: [null, [Validators.maxLength(40)]],
    updatedDate: [],
    status: [],
    profile: [],
    profileCategory: [],
    paymentSubscription: [],
  });

  constructor(
    protected userMasterService: UserMasterService,
    protected categoryMetadataService: CategoryMetadataService,
    protected profileMasterService: ProfileMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userMaster }) => {
      this.updateForm(userMaster);

      this.categoryMetadataService.query().subscribe((res: HttpResponse<ICategoryMetadata[]>) => (this.categorymetadata = res.body || []));

      this.profileMasterService.query().subscribe((res: HttpResponse<IProfileMaster[]>) => (this.profilemasters = res.body || []));
    });
  }

  updateForm(userMaster: IUserMaster): void {
    this.editForm.patchValue({
      id: userMaster.id,
      emailId: userMaster.emailId,
      password: userMaster.password,
      updatedBy: userMaster.updatedBy,
      updatedDate: userMaster.updatedDate,
      status: userMaster.status,
      profile: userMaster.profile,
      profileCategory: userMaster.profileCategory,
      paymentSubscription: userMaster.paymentSubscription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userMaster = this.createFromForm();
    if (userMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.userMasterService.update(userMaster));
    } else {
      this.subscribeToSaveResponse(this.userMasterService.create(userMaster));
    }
  }

  private createFromForm(): IUserMaster {
    return {
      ...new UserMaster(),
      id: this.editForm.get(['id'])!.value,
      emailId: this.editForm.get(['emailId'])!.value,
      password: this.editForm.get(['password'])!.value,
      updatedBy: this.editForm.get(['updatedBy'])!.value,
      updatedDate: this.editForm.get(['updatedDate'])!.value,
      status: this.editForm.get(['status'])!.value,
      profile: this.editForm.get(['profile'])!.value,
      profileCategory: this.editForm.get(['profileCategory'])!.value,
      paymentSubscription: this.editForm.get(['paymentSubscription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserMaster>>): void {
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
