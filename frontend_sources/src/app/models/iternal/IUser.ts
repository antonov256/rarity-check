export interface IUserLight {
  id: number;
  username: string;
  name: string;
  surname: string;
  email: string;
  timeZone: string;
  lastSeen: string;
  authorities: string;
}

export interface IUser extends IUserLight {
  portfolio: "";
}
