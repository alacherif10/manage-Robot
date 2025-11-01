import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private signupUrl = 'http://35.222.253.93:8080/api/auth/register';

  constructor(private http: HttpClient) { }

  signupuser(signupData: any): Observable<any> {
    return this.http.post(this.signupUrl, signupData);
  }
}
