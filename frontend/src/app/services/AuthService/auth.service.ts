import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  loginUser(email: string, password: string) {
    return this.http.post('http://localhost:8081/api/auth/login', { email, password });
  }
}
