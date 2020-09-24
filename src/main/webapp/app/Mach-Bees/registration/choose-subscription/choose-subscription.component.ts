import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ChooseSubscriptionService } from './choose-subscription.service';

@Component({
  selector: 'jhi-choose-subscription',
  templateUrl: './choose-subscription.component.html',
  styles: [],
})
export class ChooseSubscriptionComponent implements OnInit {
  choosesubscriptionmasters: [] = [];
  isSaving = false;
  editForm = this.fb.group({
    userId: [],
    subscription: ['123', [Validators.required]],
  });
  constructor(private fb: FormBuilder, private service: ChooseSubscriptionService) {}

  ngOnInit(): void {}
  save(): void {
    this.service.save();
  }
  previousState(): void {
    this.service.back();
  }
  trackById(index: number, item: any): any {
    return item.id;
  }
}
