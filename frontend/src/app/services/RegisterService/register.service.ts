import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private signupUrl = 'http://127.0.0.1:8080/api/auth/register';

  constructor(private http: HttpClient) { }

  signupuser(signupData: any): Observable<any> {
    return this.http.post(this.signupUrl, signupData);
  }
}
