import {Component, Input, OnInit} from '@angular/core';
import {Chat} from "../../store/chat-store/service/chat.model";

@Component({
  selector: 'app-chat-list-ui',
  templateUrl: './chat-list-ui.component.html',
  styles: [
  ]
})
export class ChatListUiComponent implements OnInit {
  @Input()
  chats!: Chat[]

  constructor() { }

  ngOnInit(): void {
  }

}
