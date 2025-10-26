import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, switchMap, tap } from 'rxjs';
import { AuthService } from '../AuthService/auth.service';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl= 'http://127.0.0.1:8080/api/auth/login';

  private _isLoggedIn$ = new BehaviorSubject<boolean>(false)
  isLoggedIn$ = this._isLoggedIn$.asObservable()

  constructor(private auth: AuthService, private http: HttpClient,  private router: Router) { 
    const token = localStorage.getItem('fayrouz_auth');
    this._isLoggedIn$.next(!!token);
  }

  loginUser(email: string, password: string) {
  return this.auth.loginUser(email, password).pipe(
    tap((response: any) => {
      console.log('Response:', response);
      const token = response.token;  // ✅ Correct key
      console.log('Token:', token);
      this._isLoggedIn$.next(true);
      localStorage.setItem('fayrouz_auth', token);
      this.router.navigate(['home']);
    })
  );
}

  getToken(): string {
    return localStorage.getItem('fayrouz_auth') || '';
  }

  isLoggedIn(): boolean {
    const token = localStorage.getItem('fayrouz_auth');
    return !!token;
  }
  
  getSessionId(): string | null{
    return localStorage.getItem('fayrouz_session_id');
  }

  logoutUser() {
    this._isLoggedIn$.next(false);
    localStorage.removeItem('fayrouz_auth');
  }
}
