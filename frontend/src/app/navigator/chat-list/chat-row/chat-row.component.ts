import {Component, Input, OnInit} from '@angular/core';
import {Chat} from "../../../store/chat-store/service/chat.model";

@Component({
  selector: 'app-chat-row',
  templateUrl: './chat-row.component.html',
  styles: [
  ]
})
export class ChatRowComponent implements OnInit {
  @Input()
  chat!: Chat

  constructor() { }

  ngOnInit(): void {
  }

}
