import {Component, Input, OnInit} from '@angular/core';
import {LocalChat} from "../../../store/chat-store/service/local-chat.model";
import {PrivateChat} from "../../../store/chat-store/service/private-chat.model";
import {ChatService} from "../../../store/chat-store/service/chat.service";

@Component({
  selector: 'app-chat-row',
  templateUrl: './chat-row.component.html',
  styles: []
})
export class ChatRowComponent implements OnInit {
  @Input()
  chat!: LocalChat | PrivateChat

  privateChat!: PrivateChat
  localChat!: LocalChat

  constructor() {
  }

  ngOnInit(): void {
    if (ChatService.isLocalChat(this.chat))
      this.localChat = this.chat as LocalChat
    else
      this.privateChat = this.chat as PrivateChat
  }
}
