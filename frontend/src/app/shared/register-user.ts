import { Speciality } from "./Speciality/speciality";

export interface RegisterUser {

  id:string;
  firstname: string;
  lastname: string;
  email: string;
  password: string;
  sexe: string;
  birthDate: Date;
  phone: string;
  address: string;
  active:boolean ;
  speciality?: Speciality;

  role: string;


}
