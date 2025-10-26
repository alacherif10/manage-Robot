import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/services/NotificationService/notification.service';
import { RegisterService } from 'src/app/services/RegisterService/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  signupForm: FormGroup;
  submitted = false;
  successMessage!: string;

   constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private registerservice : RegisterService, private notificationService : NotificationService) {
    this.signupForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required]
    });
   }

   get f(){return this.signupForm.controls;}

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      'firstname': new FormControl(),
      'lastname': new FormControl(),
      'email': new FormControl(),
      'password': new FormControl(),
      
    })

    this.notificationService.getNotification().subscribe(message => {
      this.successMessage = message;
    });
  }

  onSubmit(signupForm: FormGroup) {
    console.log(this.signupForm.value);
    // send POST request to backend to create user using this.signupForm.value
    this.registerservice.signupuser(this.signupForm.value)
    .subscribe(
      response => {
        console.log(response);
        this.router.navigate(['login']);
        this.notificationService.setNotification('User added successfuly');
       
      },
      error => console.log(error)
    );
  
  }

}
