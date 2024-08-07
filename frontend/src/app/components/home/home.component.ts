import { User } from 'src/app/shared/user';
import { Question } from 'src/app/shared/Question/question';
import { ReponseService } from './../../services/Reponse/reponse.service';
import { QuestionService } from './../../services/Question/question.service';
import { Speciality } from 'src/app/shared/Speciality/speciality';
import { Component,  NgModule,  OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SpecialityService } from 'src/app/services/Speciality/speciality.service';
import { UserService } from 'src/app/services/User/user.service';
import { RegisterUser } from 'src/app/shared/register-user';
import { Reponse } from 'src/app/shared/Reponse/reponse';
import { PhotoService } from 'src/app/services/Photo/photo.service';
import { FormControl } from '@angular/forms';
import { HttpResponse } from '@angular/common/http';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit {

  errMsg!: string
  isWaiting: boolean = true;
  selectedFile!: File;
  fileControl = new FormControl();

  constructor(private router: Router,
    private specialityService: SpecialityService,
    private userService: UserService,
    private questionService:QuestionService,
    private reponseService:ReponseService,
    private photoService :PhotoService){
      this.fileControl = new FormControl(this.selectedFile);
    }

  ngOnInit(): void {
    this.getSpacialties();
    this.getDoctors();
    this.getQuestions();
  }


  specialties: Speciality[]=[];
  getSpacialties(){
    this.specialityService.getSpecialties().subscribe(
      {
        next: (spl: Speciality[]) => { this.specialties = spl;  },
        error: (err) => { this.specialties = [], this.isWaiting = false; this.errMsg = err }
      })
  }

 doctors: RegisterUser[]=[];
  getDoctors() {
    this.userService.getDoctors().subscribe(
      {
        next: (doctors: RegisterUser[]) => { this.doctors = doctors; }
      })
  }
  getDoctorsBySpeciality(idSpeciality:string){
    this.getDoctors();
    var sp=this.specialityService.getSpecialityById(idSpeciality).subscribe({
      next: (spl: Speciality) => {
        this.doctors.filter(doctor => doctor.speciality === spl); },
    })
  }

  questions : Question[]=[]
  getQuestions(){
    this.questionService.getQuestions().subscribe(
      {
        next: (questions: Question[]) => { this.questions = questions; }
      })
  }

  reponses:Reponse[]=[];
  getResponseToQuestion(question:string){
    this.reponseService.getReponses().subscribe(
      {
        next: (reponses: Reponse[]) => { this.reponses = reponses; }
      })

      this.reponses.filter(reponse => reponse.question.id=== question);
  }


  image:any;
  getPhotoUser(idUser:string){
    var img='';
    this.photoService.getByUser(idUser).subscribe(res => {
      if(res)
        img='data:'+res.type+';base64,' + res.file;
    })
    return img;
  }
  valueFile(){
    this.fileControl.valueChanges.subscribe((files: any) => {
      if (!Array.isArray(files)) {
        this.selectedFile  = <File>files;
      } else {
        this.selectedFile = <File>files[0];
      }
      console.log("okkkk");
    });
  }

 /* onUpload(file :File) {
    //uploadImg
    this.photoService.uploadImg(file).subscribe(res => {
      if  (res instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });
  }*/

    onUpload(event: Event): void {
      const input = event.target as HTMLInputElement;
      if (input.files && input.files.length > 0) {

        this.photoService.uploadImg(input.files[0],"6682f6af20e64617903c67aa").subscribe(res => {
          if  (res instanceof HttpResponse) {
            console.log('File is completely uploaded!');
          }
        });

      } else {

        console.log('No files selected');
      }
    }

    name:string="";
    speciality!: Speciality;
    onSubmit(){
      this.specialityService.addSpeciality(this.speciality)
        .subscribe()
    }

    doctSpeciality(idDoctor:string){
      this.userService.getDoctorSpeciality(idDoctor).subscribe({
        next: (res: Speciality) => { return res.name; }
      })
      return "";

    }
}
