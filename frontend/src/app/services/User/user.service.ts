import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from '../ProcessHttpmsg/process-httpmsg.service';
import { Speciality } from 'src/app/shared/Speciality/speciality';
import { RegisterUser } from 'src/app/shared/register-user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true,
  };
  constructor(
    private http: HttpClient,
    @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService
  ) {}

  getUsers(): Observable<RegisterUser[]> {
    return this.http
      .get<RegisterUser[]>(this.baseUrl + "users")
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  getUserById(id: string): Observable<RegisterUser> {
    return this.http.get<RegisterUser>(this.baseUrl+"users/"+id,{ withCredentials: true});
   }

   getDoctors(): Observable<RegisterUser[]> {
    return this.http.get<RegisterUser[]>(this.baseUrl+"users/doctors");
   }

   getDoctorSpeciality(id: string): Observable<Speciality> {
     return this.http.get<Speciality>(this.baseUrl+"users/speciality/"+id,{ withCredentials: true});
    }
}
