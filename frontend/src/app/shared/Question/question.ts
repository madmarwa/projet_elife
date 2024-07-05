import { RegisterUser } from "../register-user";
import { User } from "../user";


export class Question {
  id: string;
  date: Date;
  urgent: boolean;
  texte: string;
  patient: RegisterUser;

  constructor(id: string, date: Date, urgent: boolean, texte: string, patient: RegisterUser) {
    this.id = id;
    this.date = date;
    this.urgent = urgent;
    this.texte = texte;
    this.patient = patient;
  }
}
