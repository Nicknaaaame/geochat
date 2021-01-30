import { Component, OnInit } from '@angular/core';
import {LocalChat} from "../../store/chat-store/service/local-chat.model";
import {ChatService} from "../../store/chat-store/service/chat.service";

@Component({
  selector: 'app-user-chats',
  templateUrl: './user-chats.component.html',
  styles: [
  ]
})
export class UserChatsComponent implements OnInit {
  userChats$ = this.chatService.getUserChats()

  constructor(private chatService: ChatService) { }

  ngOnInit(): void {
  }

}
