import {Component, Input, OnInit} from '@angular/core';
import {Chat} from "../../../store/chat-store/service/chat.model";
import {ChatService} from "../../../store/chat-store/service/chat.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-chosen-chat',
  templateUrl: './chosen-chat.component.html',
  styles: []
})
export class ChosenChatComponent implements OnInit {

  @Input()
  chat!: Chat

  constructor(private chatService: ChatService) {
  }

  ngOnInit(): void {
  }

  onClickJoin() {
    this.chatService.joinLocalChat(this.chat).subscribe(value => console.log(value))
  }
}
