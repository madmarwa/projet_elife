import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { ProcessHttpmsgService } from '../ProcessHttpmsg/process-httpmsg.service';
import { Horaire } from 'src/app/shared/Horaire/horaire';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HoraireService {
  httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  withCredentials: true,
};

constructor(
  private http: HttpClient,
  @Inject('BaseURL') private baseUrl: string,
  private processHTTPMsgService: ProcessHttpmsgService
) {}

getHorairesByDoctor(idDoctor:string): Observable<Horaire[]> {
  return this.http
    .get<Horaire[]>(this.baseUrl + 'horaire/doctor/'+idDoctor, { withCredentials: true })
    .pipe(catchError(this.processHTTPMsgService.handleError));
}

}
