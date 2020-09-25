import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ProfileTypeService } from './profile-type.service';
import { ActivatedRoute, Router } from '@angular/router';
import { IProfileCategory } from 'app/shared/model/MachBees/profile-Category.model';
import swal from 'sweetalert2';

@Component({
  selector: 'jhi-profile-type',
  templateUrl: './profile-type.component.html',
  styles: [],
})
export class ProfileTypeComponent implements OnInit, AfterViewInit {
  profilecategorymasters: IProfileCategory[] = [];
  isSaving = false;
  editForm = this.fb.group({
    userId: [],
    profileCategory: [null, [Validators.required]],
  });

  constructor(private fb: FormBuilder, private service: ProfileTypeService, private activeRoute: ActivatedRoute, private route: Router) {}

  ngOnInit(): void {}
  save(): void {
    this.isSaving = true;
    const serviceInput = {};
    const userId = this.editForm.get(['userId'])!.value;
    const profileCategory = this.editForm.get(['profileCategory'])!.value;
    serviceInput['userId'] = userId;
    serviceInput['profileCategory'] = profileCategory;
    this.service.save(JSON.stringify(serviceInput)).subscribe(response => {
      if (response != undefined) {
        if (response['responseStatus'] == 'failure') {
          this.processError(response);
        } else {
          this.route.navigateByUrl('registration/SelectProfile/' + userId);
        }
      }
    });
  }
  previousState(): void {
    this.route.navigateByUrl('registration/SetEmailPassword');
  }
  trackById(index: number, item: IProfileCategory): any {
    return item.id;
  }
  ngAfterViewInit(): void {
    const tmpobj = this.editForm.get('userId');
    if (tmpobj != null) {
      tmpobj.setValue(this.activeRoute.snapshot.paramMap.get('userId'));
    }

    this.service.onload().subscribe(response => {
      if (response != undefined) {
        this.profilecategorymasters = response;
      }
    });
    const userId = this.editForm.get(['userId'])!.value;
    this.service.fetch(userId).subscribe(response => {
      if (response != undefined) {
        this.editForm.patchValue(response);
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
