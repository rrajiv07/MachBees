import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProfileMaster, ProfileMaster } from 'app/shared/model/profile-master.model';
import { ProfileMasterService } from './profile-master.service';

@Component({
  selector: 'jhi-profile-master-update',
  templateUrl: './profile-master-update.component.html',
})
export class ProfileMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    profileCode: [null, [Validators.required, Validators.maxLength(20)]],
    profileName: [null, [Validators.required, Validators.maxLength(40)]],
    profileDescription: [null, [Validators.required, Validators.maxLength(80)]],
  });

  constructor(protected profileMasterService: ProfileMasterService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ profileMaster }) => {
      this.updateForm(profileMaster);
    });
  }

  updateForm(profileMaster: IProfileMaster): void {
    this.editForm.patchValue({
      id: profileMaster.id,
      profileCode: profileMaster.profileCode,
      profileName: profileMaster.profileName,
      profileDescription: profileMaster.profileDescription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const profileMaster = this.createFromForm();
    if (profileMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.profileMasterService.update(profileMaster));
    } else {
      this.subscribeToSaveResponse(this.profileMasterService.create(profileMaster));
    }
  }

  private createFromForm(): IProfileMaster {
    return {
      ...new ProfileMaster(),
      id: this.editForm.get(['id'])!.value,
      profileCode: this.editForm.get(['profileCode'])!.value,
      profileName: this.editForm.get(['profileName'])!.value,
      profileDescription: this.editForm.get(['profileDescription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProfileMaster>>): void {
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
