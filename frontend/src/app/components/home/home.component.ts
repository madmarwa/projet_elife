import { Question } from 'src/app/shared/Question/question';
import { ReponseService } from './../../services/Reponse/reponse.service';
import { QuestionService } from './../../services/Question/question.service';
import { Speciality } from 'src/app/shared/Speciality/speciality';
import { Component,  OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SpecialityService } from 'src/app/services/Speciality/speciality.service';
import { UserService } from 'src/app/services/User/user.service';
import { RegisterUser } from 'src/app/shared/register-user';
import { Reponse } from 'src/app/shared/Reponse/reponse';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit {

  errMsg!: string
  isWaiting: boolean = true;

  constructor(private router: Router,
    private specialityService: SpecialityService,
    private userService: UserService,
    private questionService:QuestionService,
    private reponseService:ReponseService){}

  ngOnInit(): void {

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
  getDoctorsBySpeciality(speciality:Speciality){
    this.getDoctors();
    this.doctors.filter(doctor => doctor.speciality === speciality);
  }

  questions : Question[]=[]
  getQuestions(){
    this.questionService.getQuestions().subscribe(
      {
        next: (questions: Question[]) => { this.questions = questions; }
      })
  }

  reponses:Reponse[]=[];
  getResponseToQuestion(question:Question){
    this.reponseService.getReponses().subscribe(
      {
        next: (reponses: Reponse[]) => { this.reponses = reponses; }
      })

      this.reponses.filter(reponse => reponse.question=== question);
  }


}
