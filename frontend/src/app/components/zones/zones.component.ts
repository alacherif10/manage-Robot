import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { ZoneService } from 'src/app/services/ZoneService/zone.service';

@Component({
  selector: 'app-zones',
  templateUrl: './zones.component.html',
  styleUrls: ['./zones.component.css']
})
export class ZonesComponent implements OnInit{
  zones: any[] = [];
  token: string = ''; 

   constructor(private zoneService: ZoneService, private apiService: ApiService) {}

  ngOnInit(): void {
    this.token = this.apiService.getToken();
    if (!this.token || this.token.split('.').length !== 3) {
      console.error('Invalid or missing JWT token.');
      return;
    }

    this.fetchZones();
}

  fetchZones() {
    this.zoneService.listZones(this.token).subscribe({
      next: (res) => {
        this.zones = res;
      },
      error: (err) => {
        console.error('Failed to fetch robots:', err);
      }
    });
  }

  deleteZone(id: string) {
    if (confirm('Are you sure you want to delete this zone?')) {
      this.zoneService.deleteZone(this.token, id).subscribe({
        next: () => {
          this.fetchZones();
        },
        error: (err) => {
          console.error('Failed to delete zone:', err);
        }
      });
    }
  }

}
