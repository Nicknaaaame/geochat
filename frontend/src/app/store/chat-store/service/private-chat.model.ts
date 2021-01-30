import {User} from "../../user-store/service/user.model";

export interface PrivateChat {
  id: number | string,
  user: User
}
