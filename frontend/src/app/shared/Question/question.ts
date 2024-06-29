import { User } from "../user";


export class Question {
  id: string;
  date: Date;
  urgent: boolean;
  texte: string;
  patient: User;

  constructor(id: string, date: Date, urgent: boolean, texte: string, patient: User) {
    this.id = id;
    this.date = date;
    this.urgent = urgent;
    this.texte = texte;
    this.patient = patient;
  }
}
