import {Component, Input, OnInit} from '@angular/core';
import {Message} from "../../store/message-store/service/message.model";
import {Store} from "@ngrx/store";
import {MessageService} from "../../store/message-store/service/message.service";
import {getProfile} from "../../store/profile-store/store/profile.selectors";
import {Chat} from "../../store/chat-store/service/chat.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-chosen-chat',
  templateUrl: './chosen-chat.component.html',
  styles: []
})
export class ChosenChatComponent implements OnInit {
  @Input()
  chat!: Chat
  messages$!: Observable<Message[]>
  userId!: number | string

  constructor(private store: Store, private messageService: MessageService) {
    store.select(getProfile).subscribe(value => this.userId = value.id)
    console.log("construct")
  }

  ngOnInit(): void {
    this.messages$ = this.messageService.getMessages(this.chat.id)
    console.log("init")
  }

  onSendMessage($event: { message: string; files: File[] }) {
    this.messageService.saveMessage({text: $event.message, chatId: this.chat.id}).subscribe(value => console.log(value))
  }
}
