import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/ApiService/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  form = new FormGroup({
    email: new FormControl(null, Validators.required),
    password: new FormControl(null, Validators.required),
  });

  constructor(private api: ApiService, private router: Router){}

  submitForm(){
    if(this.form.invalid){
      return;
    }

    const username = this.form.get('email')?.value;
    const password = this.form.get('password')?.value;
    if (username && password) {
      this.api.loginUser(username, password).subscribe(
        response => {
          // Navigation logic handled in ApiService
        },
        error => {
          console.error('Login failed', error);
        }
      );
    }
  }
}
