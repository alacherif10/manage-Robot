import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SchedulerService {

  url = 'http://127.0.0.1:8081/api/scheduler'
    
      constructor(private http : HttpClient) { }
    
      AddScheduler(token:string, data:any): Observable<any> {
        
        const headers = new HttpHeaders({
          'Authorization': `Bearer ${token}`
        });
        const baseUrl = `${this.url}/add-scheduler`;
         return this.http.post(baseUrl, data, { headers });
    
      }
    
      listSchedulers(token:string) : Observable<any>{
        const headers = new HttpHeaders({
          'Authorization': `Bearer ${token}`
        });
        const baseUrl = `${this.url}/times`;
         return this.http.get(baseUrl, { headers });
    
      }
    
      getScheduler(token:string, id : string) : Observable<any>{
        const headers = new HttpHeaders({
          'Authorization': `Bearer ${token}`
        });
        const endpoint = `${this.url}/times/${id}`;
        return this.http.get(endpoint, { headers });
      }
    
      deleteScheduler(token:string, id : string) : Observable<any>{
        const headers = new HttpHeaders({
          'Authorization': `Bearer ${token}`
        });
        const endpoint = `${this.url}/times/${id}`;
        return this.http.delete(endpoint, { headers }); 
      }
}
