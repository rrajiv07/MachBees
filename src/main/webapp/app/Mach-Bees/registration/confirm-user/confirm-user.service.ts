import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class ConfirmUserService {
  constructor(private http: HttpClient, private routerLink: Router) {}
  save(): void {
    alert('Confirm');
  }
}
