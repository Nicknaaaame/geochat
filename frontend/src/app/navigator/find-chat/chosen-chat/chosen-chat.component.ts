import {Component, Input, OnInit} from '@angular/core';
import {LocalChat} from "../../../store/chat-store/service/local-chat.model";
import {ChatService} from "../../../store/chat-store/service/chat.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-chosen-chat',
  templateUrl: './chosen-chat.component.html',
  styles: []
})
export class ChosenChatComponent implements OnInit {

  @Input()
  chat!: LocalChat

  constructor(private chatService: ChatService) {
  }

  ngOnInit(): void {
  }

  onClickJoin() {
    this.chatService.joinLocalChat(this.chat).subscribe(value => console.log(value))
  }
}
