import {Component, Input, OnInit} from '@angular/core';
import {Chat} from "../../../store/chat-store/service/chat.model";

@Component({
  selector: 'app-chat-row-ui',
  templateUrl: './chat-row-ui.component.html',
  styles: [
  ]
})
export class ChatRowUiComponent implements OnInit {
  @Input()
  chat!: Chat

  constructor() { }

  ngOnInit(): void {
  }

}
