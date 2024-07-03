import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HoraireService } from 'src/app/services/Horaire/horaire.service';
import { UserService } from 'src/app/services/User/user.service';
import { Horaire } from 'src/app/shared/Horaire/horaire';
import { RegisterUser } from 'src/app/shared/register-user';

@Component({
  selector: 'app-doctor-details',
  templateUrl: './doctor-details.component.html',
  styleUrls: ['./doctor-details.component.css']
})
export class DoctorDetailsComponent implements OnInit {
  doctor: RegisterUser | undefined;
  idDoctor: any;
  horaires:Horaire[]=[];
  constructor(private userService: UserService,
    private horaireService:HoraireService,
    private route: ActivatedRoute,
    private router: Router,
    @Inject('BaseURL') public baseURL:any) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(
      res => {
        this.idDoctor = res.get('id');
        this.userService.getUserById(this.idDoctor).subscribe(
          (doctor) => { this.doctor = doctor }
        );
      }
    )
    this.horaireService

  }

}
