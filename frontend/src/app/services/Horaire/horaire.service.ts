import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { ProcessHttpmsgService } from '../ProcessHttpmsg/process-httpmsg.service';

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
}
