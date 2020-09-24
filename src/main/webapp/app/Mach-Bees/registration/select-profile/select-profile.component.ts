import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SelectProfileService } from './select-profile.service';
import { ActivatedRoute, Router } from '@angular/router';
import { IProfileTypeCategory } from 'app/shared/model/MachBees/profile-type-category.model';
import swal from 'sweetalert2';

@Component({
  selector: 'jhi-select-profile',
  templateUrl: './select-profile.component.html',
  styles: [],
})
export class SelectProfileComponent implements OnInit, AfterViewInit {
  profiletypemasters: IProfileTypeCategory[] = [];
  isSaving = false;
  editForm = this.fb.group({
    userId: [],
    profileType: ['', [Validators.required]],
  });
  constructor(private fb: FormBuilder, private service: SelectProfileService, private activeRoute: ActivatedRoute, private route: Router) {}

  ngOnInit(): void {}
  save(): void {
    this.isSaving = true;

    const serviceInput = {};
    const userId = this.editForm.get(['userId'])!.value;
    const profileType = this.editForm.get(['profileType'])!.value;
    serviceInput['userId'] = userId;
    serviceInput['profileType'] = profileType;
    this.service.save(JSON.stringify(serviceInput)).subscribe(response => {
      console.log('response>>>>>>>>>', response);
      if (response != undefined) {
        if (response['responseStatus'] == 'failure') {
          this.processError(response);
        } else {
          this.route.navigateByUrl('registration/PersonalDetails/' + userId);
        }
      }
    });
  }
  previousState(): void {
    const userId = this.editForm.get(['userId'])!.value;
    this.route.navigateByUrl('registration/ProfileType/' + userId);
  }
  trackById(index: number, item: IProfileTypeCategory): any {
    return item.id;
  }
  ngAfterViewInit(): void {
    const tmpobj = this.editForm.get('userId');
    if (tmpobj != null) {
      tmpobj.setValue(this.activeRoute.snapshot.paramMap.get('userId'));
    }
    this.service.onload().subscribe(response => {
      if (response != undefined) {
        this.profiletypemasters = response;
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
