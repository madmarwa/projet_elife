import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { ProcessHttpmsgService } from '../ProcessHttpmsg/process-httpmsg.service';
import { Reponse } from 'src/app/shared/Reponse/reponse';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReponseService {
  httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  withCredentials: true,
};

constructor(
  private http: HttpClient,
  @Inject('BaseURL') private baseUrl: string,
  private processHTTPMsgService: ProcessHttpmsgService
) {}


getReponses(): Observable<Reponse[]> {
  return this.http
    .get<Reponse[]>(this.baseUrl + 'reponses', { withCredentials: true })
    .pipe(catchError(this.processHTTPMsgService.handleError));
}
}
