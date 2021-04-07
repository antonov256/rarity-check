export interface IUserLight {
  id: number,
  username: string,
  name: string,
  surename: string,
  email: string,
  timeZone: string,
  ownListSize: number,
  wishListSize: number
}

export interface IUser extends IUserLight {
  portfolio: ''
}
