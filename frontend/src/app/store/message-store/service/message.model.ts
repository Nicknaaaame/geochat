import {User} from "../../user-store/service/user.model";

export interface Message {
  id: number | string,
  text: string,
  messageType: string,
  sender: User,
  creationDate: Date
}
