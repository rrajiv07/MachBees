import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserMaster } from 'app/shared/model/user-master.model';

@Component({
  selector: 'jhi-user-master-detail',
  templateUrl: './user-master-detail.component.html',
})
export class UserMasterDetailComponent implements OnInit {
  userMaster: IUserMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userMaster }) => (this.userMaster = userMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
