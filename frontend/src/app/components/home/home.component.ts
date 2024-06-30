import { Speciality } from './../../shared/Speciality/speciality';
import { Component,  OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SpecialityService } from 'src/app/services/Speciality/speciality.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit {

  sp: Speciality[]=[];
  errMsg!: string
  isWaiting: boolean = true;

  constructor(private router: Router,
    private specialityService: SpecialityService){}

  ngOnInit(): void {
    this.specialityService.getSpecialties().subscribe(
      {
        next: (spl: Speciality[]) => { this.sp = spl;  },
        error: (err) => { this.sp = [], this.isWaiting = false; this.errMsg = err }
      })

  }

}
