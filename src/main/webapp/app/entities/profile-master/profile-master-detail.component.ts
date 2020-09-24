import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfileMaster } from 'app/shared/model/profile-master.model';

@Component({
  selector: 'jhi-profile-master-detail',
  templateUrl: './profile-master-detail.component.html',
})
export class ProfileMasterDetailComponent implements OnInit {
  profileMaster: IProfileMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ profileMaster }) => (this.profileMaster = profileMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
