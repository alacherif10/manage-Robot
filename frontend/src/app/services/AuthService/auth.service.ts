import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  loginUser(email: string, password: string) {
    return this.http.post('http://abbf02380dd7a49f28ea61f8c24ef0d1-1327904513.us-east-2.elb.amazonaws.com/api/auth/login', { email, password });
  }
}
