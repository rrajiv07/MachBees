import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ChooseSubscriptionService } from './choose-subscription.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ICategoryMetadata } from 'app/shared/model/MachBees/category-metadata.model';
import swal from 'sweetalert2';

@Component({
  selector: 'jhi-choose-subscription',
  templateUrl: './choose-subscription.component.html',
  styles: [],
})
export class ChooseSubscriptionComponent implements OnInit, AfterViewInit {
  choosesubscriptionmasters: ICategoryMetadata[] = [];
  isSaving = false;
  editForm = this.fb.group({
    userId: [],
    subscription: ['', [Validators.required]],
  });
  constructor(
    private fb: FormBuilder,
    private service: ChooseSubscriptionService,
    private activeRoute: ActivatedRoute,
    private route: Router
  ) {}

  ngOnInit(): void {}
  ngAfterViewInit(): void {
    const tmpobj = this.editForm.get('userId');
    if (tmpobj != null) {
      tmpobj.setValue(this.activeRoute.snapshot.paramMap.get('userId'));
    }

    this.service.onload().subscribe(response => {
      if (response != undefined) {
        this.choosesubscriptionmasters = response;
      }
    });
    const userId = this.editForm.get(['userId'])!.value;
    this.service.fetch(userId).subscribe(response => {
      if (response != undefined) {
        this.editForm.patchValue(response);
      }
    });
  }
  save(): void {
    this.isSaving = true;
    const serviceInput = {};
    const userId = this.editForm.get(['userId'])!.value;
    const subscription = this.editForm.get(['subscription'])!.value;
    serviceInput['userId'] = userId;
    serviceInput['subscription'] = subscription;
    this.service.save(JSON.stringify(serviceInput)).subscribe(response => {
      if (response != undefined) {
        if (response['responseStatus'] == 'failure') {
          this.processError(response);
        } else {
          this.route.navigateByUrl('registration/ConfirmUser/' + userId);
        }
      }
    });
  }
  previousState(): void {
    const userId = this.editForm.get(['userId'])!.value;
    this.route.navigateByUrl('registration/PersonalDetails/' + userId);
  }
  trackById(index: number, item: ICategoryMetadata): any {
    return item.id;
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
