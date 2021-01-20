import {User} from "../../user.model";

export interface Chat {
  id: number | string,
  name: string,
  admin: User,
  users: User[],
  location: Location
}
