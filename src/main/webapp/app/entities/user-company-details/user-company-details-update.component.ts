import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IUserCompanyDetails, UserCompanyDetails } from 'app/shared/model/user-company-details.model';
import { UserCompanyDetailsService } from './user-company-details.service';
import { IUserMaster } from 'app/shared/model/user-master.model';
import { UserMasterService } from 'app/entities/user-master/user-master.service';

@Component({
  selector: 'jhi-user-company-details-update',
  templateUrl: './user-company-details-update.component.html',
})
export class UserCompanyDetailsUpdateComponent implements OnInit {
  isSaving = false;
  users: IUserMaster[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(40)]],
    website: [null, [Validators.maxLength(20)]],
    description: [null, [Validators.maxLength(100)]],
    address: [null, [Validators.required, Validators.maxLength(80)]],
    vat: [null, [Validators.required, Validators.maxLength(40)]],
    mobile: [null, [Validators.required, Validators.maxLength(40)]],
    linkedin: [null, [Validators.maxLength(40)]],
    twitter: [null, [Validators.maxLength(40)]],
    skype: [null, [Validators.maxLength(40)]],
    user: [],
  });

  constructor(
    protected userCompanyDetailsService: UserCompanyDetailsService,
    protected userMasterService: UserMasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userCompanyDetails }) => {
      this.updateForm(userCompanyDetails);

      this.userMasterService
        .query({ filter: 'usercompanydetails-is-null' })
        .pipe(
          map((res: HttpResponse<IUserMaster[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IUserMaster[]) => {
          if (!userCompanyDetails.user || !userCompanyDetails.user.id) {
            this.users = resBody;
          } else {
            this.userMasterService
              .find(userCompanyDetails.user.id)
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

  updateForm(userCompanyDetails: IUserCompanyDetails): void {
    this.editForm.patchValue({
      id: userCompanyDetails.id,
      name: userCompanyDetails.name,
      website: userCompanyDetails.website,
      description: userCompanyDetails.description,
      address: userCompanyDetails.address,
      vat: userCompanyDetails.vat,
      mobile: userCompanyDetails.mobile,
      linkedin: userCompanyDetails.linkedin,
      twitter: userCompanyDetails.twitter,
      skype: userCompanyDetails.skype,
      user: userCompanyDetails.user,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userCompanyDetails = this.createFromForm();
    if (userCompanyDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.userCompanyDetailsService.update(userCompanyDetails));
    } else {
      this.subscribeToSaveResponse(this.userCompanyDetailsService.create(userCompanyDetails));
    }
  }

  private createFromForm(): IUserCompanyDetails {
    return {
      ...new UserCompanyDetails(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      website: this.editForm.get(['website'])!.value,
      description: this.editForm.get(['description'])!.value,
      address: this.editForm.get(['address'])!.value,
      vat: this.editForm.get(['vat'])!.value,
      mobile: this.editForm.get(['mobile'])!.value,
      linkedin: this.editForm.get(['linkedin'])!.value,
      twitter: this.editForm.get(['twitter'])!.value,
      skype: this.editForm.get(['skype'])!.value,
      user: this.editForm.get(['user'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserCompanyDetails>>): void {
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
