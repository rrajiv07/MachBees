import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CompanyDetailsService } from './company-details.service';
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
  selector: 'jhi-company-details',
  templateUrl: './company-details.component.html',
  styles: [],
})
export class CompanyDetailsComponent implements OnInit, AfterViewInit {
  isSaving = false;
  languagemasters: IUserSkillCategoryMetadata[] = [];
  proficiencymasters: IUserProficiencyCategoryMetadata[] = [];
  language: Language[] = [];

  editForm = this.fb.group({
    userId: [],
    companyName: ['BCT', [Validators.required, Validators.maxLength(40)]],
    vatId: ['1234', [Validators.required, Validators.maxLength(40)]],
    website: ['Chennai', [Validators.maxLength(20)]],
    description: ['India', [Validators.maxLength(100)]],
    address: ['Chennai', [Validators.required, Validators.maxLength(80)]],
    mobile: ['1234567890', [Validators.required, Validators.maxLength(40)]],
    linkedIn: [null, [Validators.maxLength(40)]],
    twitter: [null, [Validators.maxLength(40)]],
    skypeAddress: [null, [Validators.maxLength(40)]],
  });

  constructor(
    private fb: FormBuilder,
    private service: CompanyDetailsService,
    private activeRoute: ActivatedRoute,
    private route: Router
  ) {}

  ngOnInit(): void {}
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
  save(): void {
    this.isSaving = true;
    const serviceInput = {};
    const userId = this.editForm.get(['userId'])!.value;
    const companyName = this.editForm.get(['companyName'])!.value;
    const vatId = this.editForm.get(['vatId'])!.value;
    const description = this.editForm.get(['description'])!.value;
    const address = this.editForm.get(['address'])!.value;
    const website = this.editForm.get(['website'])!.value;
    const mobile = this.editForm.get(['mobile'])!.value;
    const linkedIn = this.editForm.get(['linkedIn'])!.value;
    const twitter = this.editForm.get(['twitter'])!.value;
    const skypeAddress = this.editForm.get(['skypeAddress'])!.value;

    serviceInput['userId'] = userId;
    serviceInput['companyName'] = companyName;
    serviceInput['vatId'] = vatId;
    serviceInput['address'] = address;
    serviceInput['description'] = description;
    serviceInput['website'] = website;
    serviceInput['mobile'] = mobile;
    serviceInput['linkedIn'] = linkedIn;
    serviceInput['twitter'] = twitter;
    serviceInput['skypeAddress'] = skypeAddress;
    if (this.language == null) serviceInput['language'] = null;
    else serviceInput['language'] = this.language;

    this.service.save(JSON.stringify(serviceInput)).subscribe(response => {
      if (response != undefined) {
        if (response['responseStatus'] == 'failure') {
          this.processError(response);
        } else {
          //this.route.navigateByUrl('registration/ChooseSubscription/' + userId);
        }
      }
    });
  }
  previousState(): void {
    const userId = this.editForm.get(['userId'])!.value;
    this.route.navigateByUrl('registration/SelectProfile/' + userId);
  }
  trackById(index: number, item: any): any {
    return item.id;
  }
  griddtlCheckboxClick(event: any, record: any): void {
    if (event.target.checked) record['select'] = 'true';
    else record['select'] = 'false';
  }
  gridAdd(): void {
    let recorddata = this.language;
    if (recorddata == undefined) {
      recorddata = [];
    }
    const tmpNewRecord = {};
    tmpNewRecord['language'] = '';
    tmpNewRecord['proficiency'] = '';
    tmpNewRecord['id'] = '';

    recorddata.push(tmpNewRecord);
    this.language = recorddata;
  }
  gridDelete(): void {
    const recorddata = this.language;
    let selCount = 0;
    recorddata.forEach(rec => {
      if (rec['select'] == 'true') {
        selCount = selCount + 1;
        rec['recStatus'] = 'D';
      }
    });
    if (selCount == 0) {
      swal.fire({
        type: 'error',
        title: 'Please select atleast one row to delete.',
        showConfirmButton: true,
        customClass: 'swal-popupmsg',
      });
      return;
    }
    var filerdata = recorddata.filter(elem => elem['recStatus'] !== 'D');
    this.language = filerdata.slice();
    console.log(this.language, 'this.language');
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
