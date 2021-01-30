import {User} from "../../user-store/service/user.model";

export interface LocalChat {
  id: number | string,
  name: string,
  admin: User,
  users: User[],
  location: Location
}
