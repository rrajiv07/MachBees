import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { SERVER_API_URL } from 'app/app.constants';
@Injectable({
  providedIn: 'root',
})
export class SetEmailPasswordService {
  constructor(private http: HttpClient, private routerLink: Router) {}
  save1(): void {
    this.routerLink.navigateByUrl('registration/ProfileType');
  }
  save(serviceInput: any): Observable<{}> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', '*/*')
      .set('Authorization', 'Bearer ' + localStorage.getItem('JWT_TOKEN'))
      .set('X-Requested-With', 'XMLHttpRequest');
    return this.http.post(SERVER_API_URL + 'api/register/email', serviceInput, { headers: headers });
  }
  AuthTokenGeneration(serviceInput: any): Observable<{}> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', '*/*')
      .set('X-Requested-With', 'XMLHttpRequest');
    return this.http.post(SERVER_API_URL + 'api/authenticate', serviceInput, { headers: headers });
  }
}
