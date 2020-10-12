import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ConfirmCompanyDetailsService } from './confirm-company-details.service';
import { ActivatedRoute, Router } from '@angular/router';
import { IUserSkillCategoryMetadata } from 'app/shared/model/MachBees/user-skill-category-metadata.model';
import { IUserProficiencyCategoryMetadata } from 'app/shared/model/MachBees/user-proficiency-category-metadata.model';
import swal from 'sweetalert2';

export interface Language {
  id?: number;
  language?: number;
  proficiency?: number;
}

@Component({
  selector: 'jhi-confirm-company-details',
  templateUrl: './confirm-company-details.component.html',
  styles: [],
})
export class ConfirmCompanyDetailsComponent implements OnInit, AfterViewInit {
  isSaving = false;
  languagemasters: IUserSkillCategoryMetadata[] = [];
  proficiencymasters: IUserProficiencyCategoryMetadata[] = [];
  language: Language[] = [];
  editForm = this.fb.group({
    userId: [],
    profileType: ['', []],
    companyName: ['', []],
    emailId: ['', []],
    vatId: ['', []],
    description: ['', []],
    address: ['', []],
    website: ['', []],
    mobile: ['', []],
    linkedIn: [null, []],
    twitter: [null, []],
    skypeAddress: [null, []],
  });

  constructor(
    private fb: FormBuilder,
    private service: ConfirmCompanyDetailsService,
    private activeRoute: ActivatedRoute,
    private route: Router
  ) {}

  ngOnInit(): void {}
  save(): void {
    this.isSaving = true;
    const serviceInput = {};
    const userId = this.editForm.get(['userId'])!.value;
    serviceInput['userId'] = userId;
    this.service.save(JSON.stringify(serviceInput)).subscribe(response => {
      if (response != undefined) {
        if (response['responseStatus'] == 'failure') {
          this.processError(response);
        } else {
          swal.fire({
            type: 'success',
            title: response['message'],
            showConfirmButton: true,
            customClass: 'swal-popupmsg',
          });
          window.location.href = '';
        }
      }
    });
  }
  previousState(): void {
    const userId = this.editForm.get(['userId'])!.value;
    this.route.navigateByUrl('registration/ChooseSubscription/' + userId);
  }
  ngAfterViewInit(): void {
    const tmpobj = this.editForm.get('userId');
    if (tmpobj != null) {
      tmpobj.setValue(this.activeRoute.snapshot.paramMap.get('userId'));
    }

    this.service.UserLanguageComboLoading().subscribe(response => {
      if (response != undefined) {
        this.languagemasters = response;
      }
    });
    this.service.UserProficiencyComboLoading().subscribe(response => {
      if (response != undefined) {
        this.proficiencymasters = response;
      }
    });

    const userId = this.editForm.get(['userId'])!.value;
    this.service.fetch(userId).subscribe(response => {
      if (response != undefined) {
        this.editForm.patchValue(response);
        this.language = response['language'];
      }
    });
  }
  private processError(response: any): void {
    this.isSaving = false;
    swal.fire({
      type: 'error',
      title: response['message'],
      showConfirmButton: true,
      customClass: 'swal-popupmsg',
    });
  }
}
