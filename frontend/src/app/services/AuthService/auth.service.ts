import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  loginUser(email: string, password: string) {
    return this.http.post('http://a8ebdd60968494864808f91be085f3b6-1543253353.us-east-2.elb.amazonaws.com/api/auth/login', { email, password });
  }
}
