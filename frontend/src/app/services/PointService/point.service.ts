import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PointService {

  url = 'http://127.0.0.1:8080/api/point'
      
        constructor(private http : HttpClient) { }
      
        AddPoint(token:string, data:any): Observable<any> {
          
          const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
          });
          const baseUrl = `${this.url}/add-scheduler`;
           return this.http.post(baseUrl, data, { headers });
      
        }
      
        listPoints(token:string) : Observable<any>{
          const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
          });
          const baseUrl = `${this.url}/all`;
           return this.http.get(baseUrl, { headers });
      
        }
      
        getPoint(token:string, id : string) : Observable<any>{
          const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
          });
          const endpoint = `${this.url}/${id}`;
          return this.http.get(endpoint, { headers });
        }
      
        deletePoint(token:string, id : string) : Observable<any>{
          const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
          });
          const endpoint = `${this.url}/delete/${id}`;
          return this.http.delete(endpoint, { headers }); 
        }
}
