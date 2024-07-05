import { Question } from "../Question/question";
import { RegisterUser } from "../register-user";
import { User } from "../user";

export class Reponse {
  id: string;
  dateReponse: Date;
  visible: boolean;
  texte: string;
  medecin: RegisterUser;
  question: Question;

  constructor(
    id: string,
    dateReponse: Date,
    visible: boolean,
    texte: string,
    medecin: RegisterUser,
    question: Question
  ) {
    this.id = id;
    this.dateReponse = dateReponse;
    this.visible = visible;
    this.texte = texte;
    this.medecin = medecin;
    this.question = question;
  }
}
