import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ProcessHttpmsgService } from '../ProcessHttpmsg/process-httpmsg.service';
import { Question } from 'src/app/shared/Question/question';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true,
  };

  constructor(
    private http: HttpClient,
    @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService
  ) {}

  getQuestions(): Observable<Question[]> {
    return this.http
      .get<Question[]>(this.baseUrl + 'questions', { withCredentials: true })
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  getQuestionById(id: string): Observable<Question> {
    return this.http
      .get<Question>(this.baseUrl + 'questions/' + id, { withCredentials: true })
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  deleteQuestionById(id: string): Observable<void> {
    return this.http
      .delete<void>(this.baseUrl + 'questions/' + id, { withCredentials: true })
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  addQuestion(question: Question): Observable<Question> {
    return this.http
      .post<Question>(this.baseUrl + 'questions', question, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  updateQuestion(question: Question): Observable<Question> {
    return this.http
      .put<Question>(this.baseUrl + 'questions/' + question.id, question, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }
}
