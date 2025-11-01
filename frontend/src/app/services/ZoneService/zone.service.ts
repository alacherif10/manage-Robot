import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZoneService {

  url = 'http://35.222.253.93:8080/api/scheduler'
  
    constructor(private http : HttpClient) { }
  
    AddZone(token:string, data:any): Observable<any> {
      
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
      const baseUrl = `${this.url}/add-zone`;
       return this.http.post(baseUrl, data, { headers });
  
    }
  
    listZones(token:string) : Observable<any>{
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
      const baseUrl = `${this.url}/zones`;
       return this.http.get(baseUrl, { headers });
  
    }
  
    getZone(token:string, id : string) : Observable<any>{
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
      const endpoint = `${this.url}/zones/${id}`;
      return this.http.get(endpoint, { headers });
    }
  
    deleteZone(token:string, id : string) : Observable<any>{
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
      const endpoint = `${this.url}/zones/${id}`;
      return this.http.delete(endpoint, { headers }); 
    }
}
