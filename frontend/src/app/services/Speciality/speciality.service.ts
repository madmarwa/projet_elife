import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from '../ProcessHttpmsg/process-httpmsg.service';
import { Speciality } from 'src/app/shared/Speciality/speciality';

@Injectable({
  providedIn: 'root',
})
export class SpecialityService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true,
  };
  constructor(
    private http: HttpClient,
    @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService
  ) {}

  getSpecialties(): Observable<Speciality[]> {
    return this.http
      .get<Speciality[]>(this.baseUrl + "specialites")
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  getSpecialityById(id: string): Observable<Speciality> {
    return this.http.get<Speciality>(this.baseUrl+"specialites/"+id,{ withCredentials: true});
   }
   deleteSpecialityById(id: string): Observable<void> {
      return this.http.delete<void>(this.baseUrl+"specialites/"+id,{ withCredentials: true});
   }
   addSpeciality(speciality: Speciality): Observable<Speciality> {

     return this.http.post<Speciality>(this.baseUrl+"specialites",speciality,this.httpOptions);
   }
   updateSpeciality(speciality: Speciality):Observable<Speciality>{
     return this.http.put<Speciality>(this.baseUrl+"specialites/"+speciality.id,speciality,this.httpOptions)
   }
}
