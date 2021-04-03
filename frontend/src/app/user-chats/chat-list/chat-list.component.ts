import {Component, Input, OnInit} from '@angular/core';
import {Chat} from "../../store/chat-store/service/chat.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styles: []
})
export class ChatListComponent implements OnInit {
  @Input()
  chats!: Chat[]
  indexArray: number[] = []

  constructor() {
  }

  ngOnInit(): void {
    let range = this.chats.length == 1 ? 1 : this.chats.length - 1
    for (let i = 0; i <= range; i++) {
      this.indexArray.push(i)
      i++
    }
  }
}
