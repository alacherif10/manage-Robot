import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { SchedulerService } from 'src/app/services/SchedulerService/scheduler.service';

@Component({
  selector: 'app-schedulers',
  templateUrl: './schedulers.component.html',
  styleUrls: ['./schedulers.component.css']
})
export class SchedulersComponent {
  schedulers: any[] = [];
  token: string = '';

  constructor( private schedulerService: SchedulerService, private api : ApiService, private router: Router) {}

  ngOnInit(): void {
    this.token = this.api.getToken();
    if (!this.token || this.token.split('.').length !== 3) {
      console.error('Invalid or missing JWT token.');
      return;
    }
    this.loadSchedulers();
  }

  loadSchedulers(): void {
    this.schedulerService.listSchedulers(this.token).subscribe({
      next: data => this.schedulers = data,
      error: err => console.error('Error fetching schedulers:', err)
    });
  }

  deleteScheduler(id: string): void {
    this.schedulerService.deleteScheduler(this.token, id).subscribe({
      next: () => this.loadSchedulers(),
      error: err => console.error('Error deleting scheduler:', err)
    });
  }

  
}
