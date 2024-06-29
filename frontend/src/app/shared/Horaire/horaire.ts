import { Time } from "@angular/common";
import { User } from "../user";

export class Horaire {
  id: string;
  jour: string;
  debutMatin: Time;
  finMatin: Time;
  debutApMidi: Time;
  finApMidi: Time;
  medecin: User;

  constructor(
    id: string,
    jour: string,
    debutMatin: Time,
    finMatin: Time,
    debutApMidi: Time,
    finApMidi: Time,
    medecin: User
  ) {
    this.id = id;
    this.jour = jour;
    this.debutMatin = debutMatin;
    this.finMatin = finMatin;
    this.debutApMidi = debutApMidi;
    this.finApMidi = finApMidi;
    this.medecin = medecin;
  }
}
