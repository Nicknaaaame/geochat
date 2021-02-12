import {PrivateChat} from "../../chat-store/service/private-chat.model";
import {LocalChat} from "../../chat-store/service/local-chat.model";
import {User} from "../../user-store/service/user.model";

export interface Message {
  id: number | string,
  text: string,
  messageType: string,
  privateChat: PrivateChat,
  localChat: LocalChat,
  sender: User,
  creationDate: Date
}
