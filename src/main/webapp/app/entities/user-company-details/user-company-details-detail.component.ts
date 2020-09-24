import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserCompanyDetails } from 'app/shared/model/user-company-details.model';

@Component({
  selector: 'jhi-user-company-details-detail',
  templateUrl: './user-company-details-detail.component.html',
})
export class UserCompanyDetailsDetailComponent implements OnInit {
  userCompanyDetails: IUserCompanyDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userCompanyDetails }) => (this.userCompanyDetails = userCompanyDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
