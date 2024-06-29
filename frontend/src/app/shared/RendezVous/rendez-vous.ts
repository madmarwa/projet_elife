import { User } from "../user";

export class RendezVous {
  id: string;
  dateDemande: Date;
  date: Date;
  heure: string; // Use string or a custom Time type
  valide: boolean;
  vu: boolean;
  patient: User;
  medecin: User;

  constructor(
    id: string,
    dateDemande: Date,
    date: Date,
    heure: string,
    valide: boolean,
    vu: boolean,
    patient: User,
    medecin: User
  ) {
    this.id = id;
    this.dateDemande = dateDemande;
    this.date = date;
    this.heure = heure;
    this.valide = valide;
    this.vu = vu;
    this.patient = patient;
    this.medecin = medecin;
  }
}
