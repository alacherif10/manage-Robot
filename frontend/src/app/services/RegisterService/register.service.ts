import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private signupUrl = 'http://abbf02380dd7a49f28ea61f8c24ef0d1-1327904513.us-east-2.elb.amazonaws.com/api/auth/register';

  constructor(private http: HttpClient) { }

  signupuser(signupData: any): Observable<any> {
    return this.http.post(this.signupUrl, signupData);
  }
}
