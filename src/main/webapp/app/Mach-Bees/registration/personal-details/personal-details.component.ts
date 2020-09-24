import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PersonalDetailsService } from './personal-details.service';

@Component({
  selector: 'jhi-personal-details',
  templateUrl: './personal-details.component.html',
  styles: [],
})
export class PersonalDetailsComponent implements OnInit {
  isSaving = false;
  languagemasters: [] = [];
  proficiencymasters: [] = [];

  editForm = this.fb.group({
    userId: [],
    name: ['Rajiv', [Validators.required, Validators.maxLength(40)]],
    surName: ['R', [Validators.required, Validators.maxLength(20)]],
    address: ['Chennai', [Validators.required, Validators.maxLength(80)]],
    country: ['India', [Validators.required, Validators.maxLength(40)]],
    mobile: ['123333333', [Validators.required, Validators.maxLength(40)]],
    linkedIn: [null, [Validators.maxLength(40)]],
    twitter: [null, [Validators.maxLength(40)]],
    skypeAddress: [null, [Validators.maxLength(40)]],
  });

  constructor(private fb: FormBuilder, private service: PersonalDetailsService) {}

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
