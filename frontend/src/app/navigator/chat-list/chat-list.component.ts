import {Component, Input, OnInit} from '@angular/core';
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

  constructor() { }

  ngOnInit(): void {
  }

}
