import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private signupUrl = ' a8ebdd60968494864808f91be085f3b6-1543253353.us-east-2.elb.amazonaws.com/api/auth/register';

  constructor(private http: HttpClient) { }

  signupuser(signupData: any): Observable<any> {
    return this.http.post(this.signupUrl, signupData);
  }
}
