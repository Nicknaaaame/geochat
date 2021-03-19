import {User} from "../../user-store/service/user.model";

export interface Chat {
  id: number | string,
  name: string,
  description: string,
  picture: string;
  admin: User,
  users: User[],
  location: { latitude: number, longitude: number }
}
