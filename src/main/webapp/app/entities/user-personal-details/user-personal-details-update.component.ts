import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IUserPersonalDetails, UserPersonalDetails } from 'app/shared/model/user-personal-details.model';
import { UserPersonalDetailsService } from './user-personal-details.service';
import { IUserMaster } from 'app/shared/model/user-master.model';
import { UserMasterService } from 'app/entities/user-master/user-master.service';

@Component({
  selector: 'jhi-user-personal-details-update',
  templateUrl: './user-personal-details-update.component.html',
})
export class UserPersonalDetailsUpdateComponent implements OnInit {
  isSaving = false;
  users: IUserMaster[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(40)]],
    surname: [null, [Validators.required, Validators.maxLength(20)]],
    address: [null, [Validators.required, Validators.maxLength(80)]],
    country: [null, [Validators.required, Validators.maxLength(40)]],
    mobile: [null, [Validators.required, Validators.maxLength(40)]],
    linkedin: [null, [Validators.maxLength(40)]],
    twitter: [null, [Validators.maxLength(40)]],
    skype: [null, [Validators.maxLength(40)]],
    selfPresentation: [null, [Validators.maxLength(100)]],
    virtualcv: [null, [Validators.maxLength(100)]],
    user: [],
  });

  constructor(
    protected userPersonalDetailsService: UserPersonalDetailsService,
    protected userMasterService: UserMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userPersonalDetails }) => {
      this.updateForm(userPersonalDetails);

      this.userMasterService
        .query({ filter: 'userpersonaldetails-is-null' })
        .pipe(
          map((res: HttpResponse<IUserMaster[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IUserMaster[]) => {
          if (!userPersonalDetails.user || !userPersonalDetails.user.id) {
            this.users = resBody;
          } else {
            this.userMasterService
              .find(userPersonalDetails.user.id)
              .pipe(
                map((subRes: HttpResponse<IUserMaster>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IUserMaster[]) => (this.users = concatRes));
          }
        });
    });
  }

  updateForm(userPersonalDetails: IUserPersonalDetails): void {
    this.editForm.patchValue({
      id: userPersonalDetails.id,
      name: userPersonalDetails.name,
      surname: userPersonalDetails.surname,
      address: userPersonalDetails.address,
      country: userPersonalDetails.country,
      mobile: userPersonalDetails.mobile,
      linkedin: userPersonalDetails.linkedin,
      twitter: userPersonalDetails.twitter,
      skype: userPersonalDetails.skype,
      selfPresentation: userPersonalDetails.selfPresentation,
      virtualcv: userPersonalDetails.virtualcv,
      user: userPersonalDetails.user,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userPersonalDetails = this.createFromForm();
    if (userPersonalDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.userPersonalDetailsService.update(userPersonalDetails));
    } else {
      this.subscribeToSaveResponse(this.userPersonalDetailsService.create(userPersonalDetails));
    }
  }

  private createFromForm(): IUserPersonalDetails {
    return {
      ...new UserPersonalDetails(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      surname: this.editForm.get(['surname'])!.value,
      address: this.editForm.get(['address'])!.value,
      country: this.editForm.get(['country'])!.value,
      mobile: this.editForm.get(['mobile'])!.value,
      linkedin: this.editForm.get(['linkedin'])!.value,
      twitter: this.editForm.get(['twitter'])!.value,
      skype: this.editForm.get(['skype'])!.value,
      selfPresentation: this.editForm.get(['selfPresentation'])!.value,
      virtualcv: this.editForm.get(['virtualcv'])!.value,
      user: this.editForm.get(['user'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserPersonalDetails>>): void {
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

  trackById(index: number, item: IUserMaster): any {
    return item.id;
  }
}
