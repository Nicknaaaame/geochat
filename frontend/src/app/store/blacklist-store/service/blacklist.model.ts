import {User} from "../../user-store/service/user.model";

export interface Blacklist {
  id: number | string,
  blocker: User,
  blocked: User
}
