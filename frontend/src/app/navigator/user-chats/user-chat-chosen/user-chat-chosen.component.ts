import {Component, Input, OnInit} from '@angular/core';
import {Message} from "../../../store/message-store/service/message.model";
import {Store} from "@ngrx/store";
import {LocalChat} from "../../../store/chat-store/service/local-chat.model";
import {PrivateChat} from "../../../store/chat-store/service/private-chat.model";
import {ChatService} from "../../../store/chat-store/service/chat.service";
import {MessageService} from "../../../store/message-store/service/message.service";
import {getProfile} from "../../../store/profile-store/store/profile.selectors";

@Component({
  selector: 'app-user-chat-chosen',
  templateUrl: './user-chat-chosen.component.html',
  styles: []
})
export class UserChatChosenComponent implements OnInit {
  @Input()
  messages!: Message[]
  @Input()
  chat!: LocalChat | PrivateChat
  userId!: number | string
  title!: string

  constructor(private store: Store, private messageService: MessageService) {
    store.select(getProfile).subscribe(value => this.userId = value.id)
  }

  ngOnInit(): void {
    if (ChatService.isLocalChat(this.chat)) {
      this.title = (this.chat as LocalChat).name
    } else {
      let chat = this.chat as PrivateChat
      this.title = chat.user.name
    }
  }

  getTitle(): string {
    return ChatService.getTitle(this.chat)
  }

  onSendMessage($event: { message: string; files: File[] }) {
    if (ChatService.isLocalChat(this.chat)) {
      this.messageService.saveLocalMessage({chatId: this.chat.id, text: $event.message}).subscribe()
    } else
      this.messageService.savePrivateMessage({chatId: this.chat.id, text: $event.message}).subscribe()
  }
}
