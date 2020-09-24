import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ConfirmUserService } from './confirm-user.service';

@Component({
  selector: 'jhi-confirm-user',
  templateUrl: './confirm-user.component.html',
  styles: [],
})
export class ConfirmUserComponent implements OnInit {
  isSaving = false;
  editForm = this.fb.group({
    userId: [],
    profileType: ['', []],
    name: ['Rajiv', []],
    emailId: ['abc@abc.com', []],
    surName: ['R', []],
    address: ['Chennai', []],
    country: ['India', []],
    mobile: ['123333333', []],
    linkedIn: [null, []],
    twitter: [null, []],
    skypeAddress: [null, []],
  });

  constructor(private fb: FormBuilder, private service: ConfirmUserService) {}

  ngOnInit(): void {}
  save(): void {
    this.service.save();
  }
  trackById(index: number, item: any): any {
    return item.id;
  }
}
