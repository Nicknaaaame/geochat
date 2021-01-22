import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {Chat} from "../../store/chat-store/service/chat.model";

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styles: [
  ]
})
export class ChatListComponent implements OnInit {
  @Input()
  chats!: Chat[]
  @Output()
  onChatPicked = new EventEmitter<Chat>()

  constructor() { }

  ngOnInit(): void {
  }

  onClickListItem(chat: Chat){
    this.onChatPicked.emit(chat)
  }
}
