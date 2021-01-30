import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {LocalChat} from "../../store/chat-store/service/local-chat.model";
import {PrivateChat} from "../../store/chat-store/service/private-chat.model";

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styles: [
  ]
})
export class ChatListComponent implements OnInit {
  @Input()
  chats!: Array<LocalChat | PrivateChat>
  @Output()
  onChatPicked = new EventEmitter<LocalChat | PrivateChat>()

  constructor() { }

  ngOnInit(): void {
  }

  onClickListItem(chat: LocalChat | PrivateChat){
    this.onChatPicked.emit(chat)
  }
}
