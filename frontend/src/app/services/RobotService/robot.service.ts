import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RobotService {
  url = 'http://127.0.0.1:8081/api/scheduler'

  constructor(private http : HttpClient) { }

  AddRobott(token:string, data:any): Observable<any> {
    
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    const baseUrl = `${this.url}/add-robot`;
     return this.http.post(baseUrl, data, { headers });

  }

  listRobots(token:string) : Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    const baseUrl = `${this.url}/robots`;
     return this.http.get(baseUrl, { headers });

  }

  getRobot(token:string, id : string) : Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    const endpoint = `${this.url}/robots/${id}`;
    return this.http.get(endpoint, { headers });
  }

  deleteRobot(token:string, id : string) : Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    const endpoint = `${this.url}/robots/${id}`;
    return this.http.delete(endpoint, { headers }); 
  }


}
