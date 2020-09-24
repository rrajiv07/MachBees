import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { SERVER_API_URL } from 'app/app.constants';

@Injectable({
  providedIn: 'root',
})
export class PersonalDetailsService {
  constructor(private http: HttpClient, private routerLink: Router) {}
  save(serviceInput: any): Observable<{}> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', '*/*')
      .set('Authorization', 'Bearer ' + localStorage.getItem('JWT_TOKEN'))
      .set('X-Requested-With', 'XMLHttpRequest');
    return this.http.post(SERVER_API_URL + 'api/common/registration/personalDetails', serviceInput, { headers: headers });
  }
  back(): void {
    this.routerLink.navigateByUrl('registration/SelectProfile');
  }
  UserLanguageComboLoading(): Observable<any> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', '*/*')
      .set('Authorization', 'Bearer ' + localStorage.getItem('JWT_TOKEN'))
      .set('X-Requested-With', 'XMLHttpRequest');
    return this.http.get(SERVER_API_URL + 'api/common/registration/userLanguage', { headers: headers });
  }
  UserProficiencyComboLoading(): Observable<any> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', '*/*')
      .set('Authorization', 'Bearer ' + localStorage.getItem('JWT_TOKEN'))
      .set('X-Requested-With', 'XMLHttpRequest');
    return this.http.get(SERVER_API_URL + 'api/common/registration/userProficiency', { headers: headers });
  }
}
