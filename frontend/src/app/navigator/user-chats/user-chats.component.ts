import {Component, OnInit} from '@angular/core';
import {LocalChat} from "../../store/chat-store/service/local-chat.model";
import {ChatService} from "../../store/chat-store/service/chat.service";
import {Store} from "@ngrx/store";
import {getAllChats} from "../../store/chat-store/store/chats.selectors";
import {PrivateChat} from "../../store/chat-store/service/private-chat.model";
import {Observable} from "rxjs";
import {Message} from "../../store/message-store/service/message.model";
import {loadLocalMessages, loadPrivateMessages} from "../../store/message-store/store/messages.actions";
import {getLocalMessages, getPrivateMessages} from "../../store/message-store/store/messages.selectors";
import {getProfile} from "../../store/profile-store/store/profile.selectors";

@Component({
  selector: 'app-user-chats',
  templateUrl: './user-chats.component.html',
  styles: []
})
export class UserChatsComponent implements OnInit {
  userChats$ = this.store.select(getAllChats)
  chosenChat!: PrivateChat | LocalChat
  messages$!: Observable<Message[]>

  constructor(private chatService: ChatService, private store: Store) {
  }

  ngOnInit(): void {
  }

  onChatPicked(chat: LocalChat | PrivateChat) {
    this.chosenChat = chat
    if (ChatService.isLocalChat(chat)) {
      this.store.dispatch(loadLocalMessages({chatId: chat.id}))
      this.messages$ = this.store.select(getLocalMessages(chat.id))
    } else {
      this.store.dispatch(loadPrivateMessages({chatId: chat.id}))
      this.messages$ = this.store.select(getPrivateMessages(chat.id))
    }
  }
}
