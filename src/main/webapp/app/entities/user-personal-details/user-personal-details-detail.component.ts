import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserPersonalDetails } from 'app/shared/model/user-personal-details.model';

@Component({
  selector: 'jhi-user-personal-details-detail',
  templateUrl: './user-personal-details-detail.component.html',
})
export class UserPersonalDetailsDetailComponent implements OnInit {
  userPersonalDetails: IUserPersonalDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userPersonalDetails }) => (this.userPersonalDetails = userPersonalDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
