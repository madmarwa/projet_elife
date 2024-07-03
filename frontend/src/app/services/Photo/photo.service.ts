import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { ProcessHttpmsgService } from '../ProcessHttpmsg/process-httpmsg.service';
import { Observable, catchError } from 'rxjs';
import { Photo } from 'src/app/shared/Photo/photo';
import { User } from 'src/app/shared/user';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {
  httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  withCredentials: true,
};

constructor(
  private http: HttpClient,
  @Inject('BaseURL') private baseUrl: string,
  private processHTTPMsgService: ProcessHttpmsgService
) {}

getPhotos(): Observable<Photo[]> {
  return this.http
    .get<Photo[]>(this.baseUrl + 'photos', { withCredentials: true })
    .pipe(catchError(this.processHTTPMsgService.handleError));
}

getById(id: string): Observable<Photo> {
  return this.http.get<Photo>(this.baseUrl+"photos/"+id,{ withCredentials: true});
 }

 getByUser(id: string): Observable<Photo> {
   return this.http.get<Photo>(this.baseUrl+"photos/user/"+id,{ withCredentials: true});
  }

create(photo:Photo): Observable<Photo> {
  return this.http.post<Photo>(this.baseUrl+"photos",Photo,this.httpOptions);
}

updatePhoto(photo: Photo):Observable<Photo>{
  return this.http.put<Photo>(this.baseUrl+"photos/"+photo.id,photo,this.httpOptions)
}


formData!: FormData

uploadImg(file: File,iduser:string): Observable<HttpEvent<any>> {
  this.formData = new FormData();

  this.formData.append('file', file);

  const req = new HttpRequest('POST', `${this.baseUrl}photos/photo/files/${iduser}`, this.formData, {
    reportProgress: true,
    responseType: 'json'
  });

  return this.http.request(req);
}

userImg(file: File, id: String): Observable<HttpEvent<any>> {
  this.formData = new FormData();

  this.formData.append('file', file);

  const req = new HttpRequest('POST', `${this.baseUrl}photos/user/profil/${id}`, this.formData, {
    reportProgress: true,
    responseType: 'json'

  });

  return this.http.request(req);
}

deleteById(id: string): Observable<void> {
  return this.http.delete<void>(this.baseUrl+"photos/"+id,{ withCredentials: true});
}
}
