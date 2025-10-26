import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { RobotService } from 'src/app/services/RobotService/robot.service';
import { SchedulerService } from 'src/app/services/SchedulerService/scheduler.service';
import { ZoneService } from 'src/app/services/ZoneService/zone.service';

@Component({
  selector: 'app-add-schedulers',
  templateUrl: './add-schedulers.component.html',
  styleUrls: ['./add-schedulers.component.css']
})
export class AddSchedulersComponent implements OnInit{
  schedulerForm: FormGroup;
  robots: any[] = [];
  zones: any[] = [];
   token: string = ''; 

  constructor(private fb: FormBuilder, private api : ApiService, private router : Router, private schedulerService: SchedulerService, private robotService: RobotService, private zoneService: ZoneService) {
    this.schedulerForm = this.fb.group({
      robotId: ['', Validators.required],
      zoneId: ['', Validators.required],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.token = this.api.getToken();
    if (!this.token || this.token.split('.').length !== 3) {
      console.error('Invalid or missing JWT token.');
      return;
    }
    this.loadRobots();
    this.loadZones();
  }

  loadRobots(): void {
    this.robotService.listRobots(this.token).subscribe({
      next: data => this.robots = data,
      error: err => console.error('Error fetching robots:', err)
    });
  }

  loadZones(): void {
    this.zoneService.listZones(this.token).subscribe({
      next: data => this.zones = data,
      error: err => console.error('Error fetching zones:', err)
    });
  }

  onSubmit(): void {
    if (this.schedulerForm.invalid) return;

    const formData = {
      robotId: this.schedulerForm.value.robotId,
      zoneId: this.schedulerForm.value.zoneId,
      startTime: this.schedulerForm.value.startTime,
      endTime: this.schedulerForm.value.endTime
    };

    this.schedulerService.AddScheduler(this.token, formData).subscribe({
      next: () => {
        this.router.navigate(['/schedulerss']);
      },
      error: err => console.error('Error adding scheduler:', err)
    });
  }

}
