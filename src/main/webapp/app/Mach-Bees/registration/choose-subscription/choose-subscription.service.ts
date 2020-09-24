import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class ChooseSubscriptionService {
  constructor(private http: HttpClient, private routerLink: Router) {}
  save(): void {
    this.routerLink.navigateByUrl('registration/ConfirmUser');
  }
  back(): void {
    this.routerLink.navigateByUrl('registration/PersonalDetails');
  }
}
