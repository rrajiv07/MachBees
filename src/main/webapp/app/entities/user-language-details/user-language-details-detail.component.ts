import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserLanguageDetails } from 'app/shared/model/user-language-details.model';

@Component({
  selector: 'jhi-user-language-details-detail',
  templateUrl: './user-language-details-detail.component.html',
})
export class UserLanguageDetailsDetailComponent implements OnInit {
  userLanguageDetails: IUserLanguageDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userLanguageDetails }) => (this.userLanguageDetails = userLanguageDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
