import { Question } from "../Question/question";
import { User } from "../user";

export class Reponse {
  id: string;
  dateReponse: Date;
  visible: boolean;
  texte: string;
  medecin: User;
  question: Question;

  constructor(
    id: string,
    dateReponse: Date,
    visible: boolean,
    texte: string,
    medecin: User,
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
