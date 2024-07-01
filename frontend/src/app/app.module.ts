import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HttpInterceptor } from './services/Interceptor/http.interceptor';
import { BaseURL } from './shared/baseurl';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { DoctorDetailsComponent } from './components/doctor-details/doctor-details.component';
import { DoctorBoardComponent } from './components/doctor-board/doctor-board.component';
import { AdminBoardComponent } from './components/admin-board/admin-board.component';
import { PatientBoardComponent } from './components/patient-board/patient-board.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SigninComponent,
    SignupComponent,
    DoctorDetailsComponent,
    DoctorBoardComponent,
    AdminBoardComponent,
    PatientBoardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptor, multi: true },
    { provide: 'BaseURL', useValue: BaseURL}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
