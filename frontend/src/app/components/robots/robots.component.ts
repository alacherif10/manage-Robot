import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { RobotService } from 'src/app/services/RobotService/robot.service';

@Component({
  selector: 'app-robots',
  templateUrl: './robots.component.html',
  styleUrls: ['./robots.component.css']
})
export class RobotsComponent implements OnInit {
  robots: any[] = [];
  token: string = '';  
  addRobotForm: FormGroup;
  showAddForm = false;

  constructor(private robotService: RobotService, private apiService: ApiService, private fb: FormBuilder) {
    this.addRobotForm = this.fb.group({
      status: [''],
      batteryLevel: [0],
      waterLevel: [0]
    });
  }

 ngOnInit(): void {
  this.token = this.apiService.getToken();
  if (!this.token || this.token.split('.').length !== 3) {
    console.error('Invalid or missing JWT token.');
    return;
  }

  this.fetchRobots();
}

  fetchRobots() {
    this.robotService.listRobots(this.token).subscribe({
      next: (res) => {
        this.robots = res;
      },
      error: (err) => {
        console.error('Failed to fetch robots:', err);
      }
    });
  }

  deleteRobot(id: string) {
    if (confirm('Are you sure you want to delete this robot?')) {
      this.robotService.deleteRobot(this.token, id).subscribe({
        next: () => {
          this.fetchRobots();
        },
        error: (err) => {
          console.error('Failed to delete robot:', err);
        }
      });
    }
  }

  addRobot() {
    this.robotService.AddRobott(this.token, this.addRobotForm.value).subscribe({
      next: () => {
        this.fetchRobots();
        this.showAddForm = false;
        this.addRobotForm.reset();
      },
      error: (err) => {
        console.error('Failed to add robot:', err);
      }
    });
  }

  toggleAddForm() {
    this.showAddForm = !this.showAddForm;
  }
}
