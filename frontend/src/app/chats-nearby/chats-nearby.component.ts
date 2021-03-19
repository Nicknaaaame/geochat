import {Component, OnInit} from '@angular/core';
import {GeolocationService} from "@ng-web-apis/geolocation";
import {ChatService} from "../store/chat-store/service/chat.service";
import {Chat} from "../store/chat-store/service/chat.model";

@Component({
  selector: 'app-chats-nearby',
  templateUrl: './chats-nearby.component.html',
  styles: []
})
export class ChatsNearbyComponent implements OnInit {
  chats$ = this.chatService.getChatsNearby()
  chosenChat!: Chat

  constructor(public geolocation$: GeolocationService, public chatService: ChatService) {
  }

  ngOnInit(): void {
  }

  onChatPicked($event: Chat) {
    this.chosenChat = $event
  }
}
