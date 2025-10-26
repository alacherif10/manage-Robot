import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/ApiService/api.service';
import { ZoneService } from 'src/app/services/ZoneService/zone.service';

@Component({
  selector: 'app-add-zone',
  templateUrl: './add-zone.component.html',
  styleUrls: ['./add-zone.component.css']
})
export class AddZoneComponent implements OnInit{

  addZoneForm: FormGroup;
  
  constructor(private zoneService: ZoneService, private apiService: ApiService, private fb: FormBuilder, private router : Router) {
    this.addZoneForm = this.fb.group({
            name: [''],
            
          });
  }

  ngOnInit(): void {
    
  }

  addZone() {
      const token = this.apiService.getToken();
      if(token){
        this.zoneService.AddZone(token, this.addZoneForm.value).subscribe({
        next: () => {
          this.router.navigate(['/zones']);
        },
        error: (err) => {
          console.error('Failed to add zone:', err);
        }
      });
  }
      }

}
