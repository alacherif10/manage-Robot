import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { RobotService } from 'src/app/services/RobotService/robot.service';

@Component({
  selector: 'app-add-robot',
  templateUrl: './add-robot.component.html',
  styleUrls: ['./add-robot.component.css']
})
export class AddRobotComponent implements OnInit{
    addRobotForm: FormGroup;

    constructor(private robotService: RobotService, private apiService: ApiService, private fb: FormBuilder, private router : Router) {
        this.addRobotForm = this.fb.group({
          status: [''],
          batteryLevel: [0],
          waterLevel: [0]
        });
      }

      ngOnInit(): void {
        
      }


    addRobot() {
      const token = this.apiService.getToken();
      if(token){
        this.robotService.AddRobott(token, this.addRobotForm.value).subscribe({
        next: () => {
          this.router.navigate(['/robots']);
        },
        error: (err) => {
          console.error('Failed to add robot:', err);
        }
      });
  }
      }
      
}
