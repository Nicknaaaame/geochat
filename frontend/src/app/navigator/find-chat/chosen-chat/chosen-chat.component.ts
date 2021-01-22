import {Component, Input, OnInit} from '@angular/core';
import {Chat} from "../../../store/chat-store/service/chat.model";

@Component({
  selector: 'app-chosen-chat',
  templateUrl: './chosen-chat.component.html',
  styles: [
  ]
})
export class ChosenChatComponent implements OnInit {

  @Input()
  chat!: Chat

  constructor() { }

  ngOnInit(): void {
  }

}
