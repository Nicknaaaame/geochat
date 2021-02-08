import {Component, OnInit} from '@angular/core';
import {LocalChat} from "../../store/chat-store/service/local-chat.model";
import {ChatService} from "../../store/chat-store/service/chat.service";
import {Store} from "@ngrx/store";
import {getAllChats} from "../../store/chat-store/store/chats.selectors";

@Component({
  selector: 'app-user-chats',
  templateUrl: './user-chats.component.html',
  styles: []
})
export class UserChatsComponent implements OnInit {
  userChats$ = this.store.select(getAllChats)

  constructor(private chatService: ChatService, private store: Store) {
  }

  ngOnInit(): void {
  }

}
