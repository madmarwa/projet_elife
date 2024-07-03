import { User } from "../user";

export class Photo {
  id: string;
  name : string;
  file: any;
  type: string;
  photoProfil: boolean;
  shareDate: Date;
  user: User;

  constructor(
    id: string,
    name: string,
    file: any,
    type: string,
    photoProfil: boolean,
    shareDate: Date,
    user: User
  ) {
    this.id = id;
    this.name=name;
    this.file = file;
    this.type = type;
    this.photoProfil = photoProfil;
    this.shareDate = shareDate;
    this.user = user;
  }
}
